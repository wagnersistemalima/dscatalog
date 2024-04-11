package com.devsuperior.dscatalog.adapters.controller.validation.category;

import com.devsuperior.dscatalog.adapters.controller.category.entity.CategoryRequest;
import com.devsuperior.dscatalog.adapters.repository.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class ProibeNomeCategoriaDuplicadoValidation implements Validator {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public boolean supports(Class<?> clazz) {
        return CategoryRequest.class.isAssignableFrom(clazz);
    }

    @Transactional(readOnly = true)
    @Override
    public void validate(Object target, Errors errors) {

        if (errors.hasErrors()) {
            return;
        }
        CategoryRequest categoryRequest = (CategoryRequest) target;

        if (categoryRepository.findByName(categoryRequest.getName()).isPresent()) {
            errors.rejectValue("name", "400",
                    "Já existe um categoria com o mesmo nome "
                            + categoryRequest.getName());
        }
    }
}
