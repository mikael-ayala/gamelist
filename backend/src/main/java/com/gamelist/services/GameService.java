package com.gamelist.services;

import com.gamelist.dto.GameDTO;
import com.gamelist.dto.GameMinDTO;
import com.gamelist.entities.Game;
import com.gamelist.projections.GameMinProjection;
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
    public List<GameMinDTO> findAll(String name, String genreId, String platformId) {
        Long parsedGenreId = parseRequestParamToLong(genreId);
        Long parsedPlatformId = parseRequestParamToLong(platformId);
        List<GameMinProjection> games = gameRepository.searchByNameAndGenreIdAndPlatormId(name, parsedGenreId, parsedPlatformId);
        return games.stream().map(GameMinDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public GameDTO findById(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new GameDTO(game);
    }

    private Long parseRequestParamToLong(String requestParam) {
        Long parsedParam = null;
        if (!requestParam.equals("0")) parsedParam = Long.parseLong(requestParam);
        return parsedParam;
    }
}
