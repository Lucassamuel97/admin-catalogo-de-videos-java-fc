package com.fullcycle.admin.catalago.infrastructure.api.controllers;

import com.fullcycle.admin.catalago.domain.pagination.Pagination;
import com.fullcycle.admin.catalago.infrastructure.api.GenreAPI;
import com.fullcycle.admin.catalago.infrastructure.genre.models.CreateGenreRequest;
import com.fullcycle.admin.catalago.infrastructure.genre.models.GenreListResponse;
import com.fullcycle.admin.catalago.infrastructure.genre.models.GenreResponse;
import com.fullcycle.admin.catalago.infrastructure.genre.models.UpdateGenreRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenreController implements GenreAPI {
    @Override
    public ResponseEntity<?> create(final CreateGenreRequest input) {
        return null;
    }

    @Override
    public Pagination<GenreListResponse> list(String search, int page, int perPage, String sort, String direction) {
        return null;
    }

    @Override
    public GenreResponse getById(String id) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateById(String id, UpdateGenreRequest input) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }
}
