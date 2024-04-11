package com.devsuperior.dscatalog.adapters.controller.category.entity;

import com.devsuperior.dscatalog.domain.Category;

import java.io.Serializable;

public class CategoryResponse implements Serializable {

    private Long id;
    private String name;

    @Deprecated
    public CategoryResponse() {
    }

    public CategoryResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryResponse(Category category) {
        id = category.getId();
        name = category.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CategoryResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
