package com.gamelist.dto;

import com.gamelist.entities.Platform;

public record PlatformDTO(Long id, String name) {
    public PlatformDTO(Platform platform) {
        this(platform.getId(), platform.getName());
    }
}
