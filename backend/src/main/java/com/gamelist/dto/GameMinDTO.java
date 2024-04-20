package com.gamelist.dto;

import com.gamelist.entities.Game;

public record GameMinDTO(Long id, String name, String imgUrl) {
    public GameMinDTO(Game game) {
        this(game.getId(), game.getName(), game.getImgUrl());
    }
}
