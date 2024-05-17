package com.gamelist.dto;

import com.gamelist.projections.GameMinProjection;

public record GameMinDTO(Long id, String name, String imgUrl) {
    public GameMinDTO(GameMinProjection gameMinProjection) {
        this(gameMinProjection.getId(), gameMinProjection.getName(), gameMinProjection.getImage());
    }
}
