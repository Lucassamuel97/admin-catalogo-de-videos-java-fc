package com.fullcycle.admin.catalago.infrastructure.api.controllers;

import com.fullcycle.admin.catalago.application.genre.create.CreateGenreCommand;
import com.fullcycle.admin.catalago.application.genre.create.CreateGenreUseCase;
import com.fullcycle.admin.catalago.application.genre.retrieve.get.GetGenreByIdUseCase;
import com.fullcycle.admin.catalago.application.genre.update.UpdateGenreCommand;
import com.fullcycle.admin.catalago.application.genre.update.UpdateGenreUseCase;
import com.fullcycle.admin.catalago.domain.pagination.Pagination;
import com.fullcycle.admin.catalago.infrastructure.api.GenreAPI;
import com.fullcycle.admin.catalago.infrastructure.genre.models.CreateGenreRequest;
import com.fullcycle.admin.catalago.infrastructure.genre.models.GenreListResponse;
import com.fullcycle.admin.catalago.infrastructure.genre.models.GenreResponse;
import com.fullcycle.admin.catalago.infrastructure.genre.models.UpdateGenreRequest;
import com.fullcycle.admin.catalago.infrastructure.genre.presenters.GenreApiPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class GenreController implements GenreAPI {

    private final CreateGenreUseCase createGenreUseCase;
    private final GetGenreByIdUseCase getGenreByIdUseCase;
    private final UpdateGenreUseCase updateGenreUseCase;

    public GenreController(
            CreateGenreUseCase createGenreUseCase,
            GetGenreByIdUseCase getGenreByIdUseCase,
            UpdateGenreUseCase updateGenreUseCase
    ) {
        this.createGenreUseCase = createGenreUseCase;
        this.getGenreByIdUseCase = getGenreByIdUseCase;
        this.updateGenreUseCase = updateGenreUseCase;
    }

    @Override
    public ResponseEntity<?> create(final CreateGenreRequest input) {
        final var aCommand = CreateGenreCommand.with(
                input.name(),
                input.isActive(),
                input.categories()
        );

        final var output = this.createGenreUseCase.execute(aCommand);

        return ResponseEntity.created(URI.create("/genres/" + output.id())).body(output);
    }

    @Override
    public Pagination<GenreListResponse> list(String search, int page, int perPage, String sort, String direction) {
        return null;
    }

    @Override
    public GenreResponse getById(final String id) {
        return GenreApiPresenter.present(this.getGenreByIdUseCase.execute(id));
    }

    @Override
    public ResponseEntity<?> updateById(final String id, final UpdateGenreRequest input) {
        final var aCommand = UpdateGenreCommand.with(
                id,
                input.name(),
                input.isActive(),
                input.categories()
        );

        final var output = this.updateGenreUseCase.execute(aCommand);

        return ResponseEntity.ok(output);
    }

    @Override
    public void deleteById(String id) {

    }
}
