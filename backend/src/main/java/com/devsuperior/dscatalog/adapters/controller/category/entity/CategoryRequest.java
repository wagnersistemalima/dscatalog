package com.devsuperior.dscatalog.adapters.controller.category.entity;

import com.devsuperior.dscatalog.domain.Category;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

public class CategoryRequest implements Serializable {

    @NotBlank
    @Length(max = 20)
    private String name;

    @Deprecated
    public CategoryRequest() {
    }

    public CategoryRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CategoryRequest{" +
                "name='" + name + '\'' +
                '}';
    }

    public Category toDomain() {
        return new Category(
                name = this.getName()
        );
    }
}
