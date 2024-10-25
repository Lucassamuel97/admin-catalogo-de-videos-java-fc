package com.fullcycle.admin.catalago.infrastructure.api.controllers;

import com.fullcycle.admin.catalago.domain.pagination.Pagination;
import com.fullcycle.admin.catalago.infrastructure.api.CategoryAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController implements CategoryAPI {
    @Override
    public ResponseEntity<?> createCategory() {
        return null;
    }

    @Override
    public Pagination<?> listCategories(String search, int page, int perPage, String sort, String direction) {
        return null;
    }
}
