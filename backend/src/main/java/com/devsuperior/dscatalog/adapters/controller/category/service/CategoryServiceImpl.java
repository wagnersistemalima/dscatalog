package com.devsuperior.dscatalog.adapters.controller.category.service;

import com.devsuperior.dscatalog.adapters.controller.exceptions.ResourceNotFoundException;
import com.devsuperior.dscatalog.adapters.repository.category.CategoryRepository;
import com.devsuperior.dscatalog.adapters.repository.category.entity.CategoryEntity;
import com.devsuperior.dscatalog.application.ports.inputs.CategoryService;
import com.devsuperior.dscatalog.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Category> findAll() {
        List<CategoryEntity> listCategoryEntity = (List<CategoryEntity>) repository.findAll();
        return listCategoryEntity.stream().map(Category::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public Category findById(Long anId) {
        CategoryEntity categoryEntity = repository.findById(anId).orElseThrow(
                () -> new ResourceNotFoundException("id de categoria não encontrado!")
        );
        return categoryEntity.toModel();
    }

    @Transactional
    @Override
    public Category create(Category anCategory) {
        return repository.save(anCategory.toEntity()).toModel();
    }
}
