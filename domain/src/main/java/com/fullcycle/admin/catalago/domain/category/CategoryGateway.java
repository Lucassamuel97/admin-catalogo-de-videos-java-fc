package com.fullcycle.admin.catalago.domain.category;

import com.fullcycle.admin.catalago.domain.pagination.SearchQuery;
import com.fullcycle.admin.catalago.domain.pagination.Pagination;

import java.util.Optional;

public interface CategoryGateway {

    Category create(Category aCategory);

    void deleteById(CategoryID anId);

    Optional<Category> findById(CategoryID anId);

    Category update(Category aCategory);

    Pagination<Category> findAll(SearchQuery aQuery);
}
