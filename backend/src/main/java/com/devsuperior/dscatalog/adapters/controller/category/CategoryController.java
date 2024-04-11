package com.devsuperior.dscatalog.adapters.controller.category;

import com.devsuperior.dscatalog.adapters.controller.category.entity.CategoryRequest;
import com.devsuperior.dscatalog.adapters.controller.category.entity.CategoryResponse;
import com.devsuperior.dscatalog.adapters.controller.validation.category.ProibeNomeCategoriaDuplicadoValidation;
import com.devsuperior.dscatalog.adapters.controller.entity.DefaultResponse;
import com.devsuperior.dscatalog.application.ports.inputs.CategoryService;
import com.devsuperior.dscatalog.domain.Category;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Validated
@RestController
@RequestMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProibeNomeCategoriaDuplicadoValidation proibeNomeCategoriaDuplicadoValidation;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(proibeNomeCategoriaDuplicadoValidation);
    }

    @GetMapping
    public ResponseEntity<DefaultResponse<List<CategoryResponse>>> findAll() {
        List<Category> listCategory = categoryService.findAll();
        List<CategoryResponse> listCategoryResponse = listCategory.stream().map(CategoryResponse::new).toList();
        DefaultResponse<List<CategoryResponse>> response = new DefaultResponse<>(listCategoryResponse);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DefaultResponse<CategoryResponse>> findById(@PathVariable("id") Long Id) {
        Category category = categoryService.findById(Id);
        CategoryResponse categoryResponse = new CategoryResponse(category);
        DefaultResponse<CategoryResponse> response = new DefaultResponse<>(categoryResponse);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<DefaultResponse<CategoryResponse>> create(
            @Valid @RequestBody CategoryRequest categoryRequest
    ) {
        Category category = categoryService.create(categoryRequest.toDomain());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(uri).body(new DefaultResponse<>(category.toResponse()));
    }
}
