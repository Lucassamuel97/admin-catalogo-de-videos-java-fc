package com.fullcycle.admin.catalago.application.genre.update;

import com.fullcycle.admin.catalago.domain.genre.Genre;

public record UpdateGenreOutput(String id) {

    public static UpdateGenreOutput from(final Genre aGenre) {
        return new UpdateGenreOutput(aGenre.getId().getValue());
    }
}
