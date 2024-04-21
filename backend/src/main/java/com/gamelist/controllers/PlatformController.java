package com.gamelist.controllers;

import com.gamelist.dto.PlatformDTO;
import com.gamelist.services.PlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/platforms")
@RequiredArgsConstructor
public class PlatformController {
    private final PlatformService platformService;

    @GetMapping
    public ResponseEntity<List<PlatformDTO>> findAll() {
        List<PlatformDTO> platforms = platformService.findAll();
        return ResponseEntity.ok(platforms);
    }
}
