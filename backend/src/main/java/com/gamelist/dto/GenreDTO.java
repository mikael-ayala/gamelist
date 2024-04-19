package com.gamelist.dto;

import com.gamelist.entities.Genre;

public record GenreDTO(Long id, String name) {
    public GenreDTO(Genre genre) {
        this(genre.getId(), genre.getName());
    }
}
