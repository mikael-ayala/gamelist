package com.gamelist.services;

import com.gamelist.dto.GenreDTO;
import com.gamelist.entities.Genre;
import com.gamelist.repositories.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public List<GenreDTO> findAll() {
        List<Genre> genres = genreRepository.findAll();
        return genres.stream().map(GenreDTO::new).toList();
    }
}
