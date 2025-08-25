package com.fullcycle.admin.catalogo.domain.category;

import com.fullcycle.admin.catalogo.domain.pagination.Pagination;

import java.util.Optional;

public interface CategoryGateway {

    Category create(Category aCategory);

    Category deleteById(CategoryID aCategoryId);

    Pagination<Category> findAll(CategorySearchQuery aQuery);

    Optional<Category> findById(CategoryID aCategoryId);

    Category update(Category aCategory);

}
