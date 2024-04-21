package com.gamelist.controllers;

import com.gamelist.dto.GameDTO;
import com.gamelist.dto.GameMinDTO;
import com.gamelist.services.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @GetMapping
    public ResponseEntity<List<GameMinDTO>> findAll(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "genreId", defaultValue = "0") String genreId
    ) {
        List<GameMinDTO> games = gameService.findAll(name, genreId);
        return ResponseEntity.ok(games);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDTO> findById(@PathVariable Long id) {
        GameDTO game = gameService.findById(id);
        return ResponseEntity.ok(game);
    }
}
