package com.fullcycle.admin.catalogo.application.category;

import com.fullcycle.admin.catalogo.domain.category.Category;

public class UseCase {
    public Category execute() {
        return  Category.newCategory(
                "name",
                "test",
                true
        );
    }
}