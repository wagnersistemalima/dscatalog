package com.devsuperior.dscatalog.application.ports.inputs;

import com.devsuperior.dscatalog.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findAll();

    Category findById(Long anId);

    Category create(Category anCategory);
}
