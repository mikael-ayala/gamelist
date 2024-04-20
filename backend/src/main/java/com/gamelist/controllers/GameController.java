package com.gamelist.controllers;

import com.gamelist.dto.GameDTO;
import com.gamelist.dto.GameMinDTO;
import com.gamelist.services.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @GetMapping
    public List<GameMinDTO> findAll(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "genreId", defaultValue = "0") String genreId
    ) {
        return gameService.findAll(name, genreId);
    }

    @GetMapping("/{id}")
    public GameDTO findById(@PathVariable Long id) {
        return gameService.findById(id);
    }
}
