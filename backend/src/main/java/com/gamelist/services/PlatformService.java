package com.gamelist.services;

import com.gamelist.dto.PlatformDTO;
import com.gamelist.entities.Platform;
import com.gamelist.repositories.PlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlatformService {
    private final PlatformRepository platformRepository;

    @Transactional(readOnly = true)
    public List<PlatformDTO> findAll() {
        List<Platform> platforms = platformRepository.findAll();
        return platforms.stream().map(PlatformDTO::new).toList();
    }
}
