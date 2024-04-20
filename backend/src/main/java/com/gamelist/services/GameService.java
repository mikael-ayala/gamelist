package com.gamelist.services;

import com.gamelist.dto.GameDTO;
import com.gamelist.dto.GameMinDTO;
import com.gamelist.entities.Game;
import com.gamelist.repositories.GameRepository;
import com.gamelist.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll(String name, String genreId) {
        Long parsedGenreId = null;
        if (!genreId.equals("0")) parsedGenreId = Long.parseLong(genreId);
        List<Game> games = gameRepository.searchByNameAndGenreId(name, parsedGenreId);
        return games.stream().map(GameMinDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public GameDTO findById(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new GameDTO(game);
    }
}
