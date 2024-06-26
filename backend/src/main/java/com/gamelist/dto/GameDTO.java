package com.gamelist.dto;

import com.gamelist.entities.Game;

import java.util.List;

public record GameDTO(
        Long id,
        String name,
        String description,
        Integer releaseYear,
        String imgUrl,
        String backgroundImgUrl,
        String videoUrl,
        List<GenreDTO> genres,
        List<PlatformDTO> platforms
) {
    public GameDTO(Game game) {
        this(game.getId(),
            game.getName(),
            game.getDescription(),
            game.getReleaseYear(),
            game.getImgUrl(),
            game.getBackgroundImgUrl(),
            game.getVideoUrl(),
            game.getGenres().stream().map(GenreDTO::new).toList(),
            game.getPlatforms().stream().map(PlatformDTO::new).toList());
    }
}
