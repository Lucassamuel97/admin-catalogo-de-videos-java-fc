package com.fullcycle.admin.catalago.application.genre.retrieve.list;

import com.fullcycle.admin.catalago.application.UseCase;
import com.fullcycle.admin.catalago.domain.pagination.Pagination;
import com.fullcycle.admin.catalago.domain.pagination.SearchQuery;

public abstract class ListGenreUseCase
        extends UseCase<SearchQuery, Pagination<GenreListOutput>> {
}

