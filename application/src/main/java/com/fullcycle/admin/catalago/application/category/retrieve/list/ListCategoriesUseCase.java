package com.fullcycle.admin.catalago.application.category.retrieve.list;

import com.fullcycle.admin.catalago.application.UseCase;
import com.fullcycle.admin.catalago.domain.pagination.SearchQuery;
import com.fullcycle.admin.catalago.domain.pagination.Pagination;

public abstract class ListCategoriesUseCase
        extends UseCase<SearchQuery, Pagination<CategoryListOutput>> {
}
