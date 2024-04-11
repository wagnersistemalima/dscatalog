package com.devsuperior.dscatalog.domain;

import com.devsuperior.dscatalog.adapters.controller.category.entity.CategoryResponse;
import com.devsuperior.dscatalog.adapters.repository.category.entity.CategoryEntity;

import java.io.Serializable;

public class Category implements Serializable {

    private Long id;

    private String name;

    @Deprecated
    public Category() {
    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(CategoryEntity categoryEntity) {
        id = categoryEntity.getId();
        name = categoryEntity.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryResponse toResponse() {
        return new CategoryResponse(
                id = this.getId(),
                name = this.getName()
        );
    }

    public CategoryEntity toEntity() {
        return new CategoryEntity(
            id = this.getId(),
            name = this.getName()
        );
    }
}
