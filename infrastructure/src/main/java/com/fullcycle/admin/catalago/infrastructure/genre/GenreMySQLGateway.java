package com.fullcycle.admin.catalago.infrastructure.genre;

import com.fullcycle.admin.catalago.domain.genre.Genre;
import com.fullcycle.admin.catalago.domain.genre.GenreGateway;
import com.fullcycle.admin.catalago.domain.genre.GenreID;
import com.fullcycle.admin.catalago.domain.pagination.Pagination;
import com.fullcycle.admin.catalago.domain.pagination.SearchQuery;
import com.fullcycle.admin.catalago.infrastructure.genre.persistence.GenreJpaEntity;
import com.fullcycle.admin.catalago.infrastructure.genre.persistence.GenreRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class GenreMySQLGateway implements GenreGateway {

    private final GenreRepository genreRepository;

    public GenreMySQLGateway(final GenreRepository genreRepository) {
        this.genreRepository = Objects.requireNonNull(genreRepository);
    }

    @Override
    public Genre create(final Genre aGenre) {
        return save(aGenre);
    }

    @Override
    public void deleteById(GenreID anId) {

    }

    @Override
    public Optional<Genre> findById(GenreID anId) {
        return Optional.empty();
    }

    @Override
    public Genre update(Genre aGenre) {
        return null;
    }

    @Override
    public Pagination<Genre> findAll(SearchQuery aQuery) {
        return null;
    }

    @Override
    public List<GenreID> existsByIds(Iterable<GenreID> ids) {
        return List.of();
    }

    private Genre save(final Genre aGenre) {
        return this.genreRepository.save(GenreJpaEntity.from(aGenre))
                .toAggregate();
    }
}
