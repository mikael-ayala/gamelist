package com.gamelist.services;

import com.gamelist.dto.GameDTO;
import com.gamelist.dto.GameMinDTO;
import com.gamelist.dto.GenreDTO;
import com.gamelist.dto.PlatformDTO;
import com.gamelist.entities.Game;
import com.gamelist.entities.Genre;
import com.gamelist.entities.Platform;
import com.gamelist.projections.GameMinProjection;
import com.gamelist.repositories.GameRepository;
import com.gamelist.repositories.GenreRepository;
import com.gamelist.repositories.PlatformRepository;
import com.gamelist.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final PlatformRepository platformRepository;
    private final GenreRepository genreRepository;

    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll(String name, String genreId, String platformId) {
        Long parsedGenreId = parseRequestParamToLong(genreId);
        Long parsedPlatformId = parseRequestParamToLong(platformId);
        List<GameMinProjection> games = gameRepository.searchByNameAndGenreIdAndPlatformId(name, parsedGenreId, parsedPlatformId);
        return games.stream().map(GameMinDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public GameDTO findById(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new GameDTO(game);
    }

    @Transactional
    public GameDTO save(GameDTO body) {
        Game newGame = new Game();
        newGame.setName(body.name());
        newGame.setImgUrl(body.imgUrl());
        newGame.setDescription(body.description());
        newGame.setReleaseYear(body.releaseYear());
        newGame.setImgUrl(body.imgUrl());
        newGame.setBackgroundImgUrl(body.backgroundImgUrl());
        newGame.setVideoUrl(body.videoUrl());

        for (PlatformDTO platformDTO : body.platforms()) {
            Platform newPlatform = platformRepository.getReferenceById(platformDTO.id());
            newGame.getPlatforms().add(newPlatform);
        }

        for (GenreDTO genreDTO : body.genres()) {
            Genre newGenre = genreRepository.getReferenceById(genreDTO.id());
            newGame.getGenres().add(newGenre);
        }

        gameRepository.save(newGame);

        return new GameDTO(newGame);
    }

    private Long parseRequestParamToLong(String requestParam) {
        Long parsedParam = null;
        if (!requestParam.equals("0")) parsedParam = Long.parseLong(requestParam);
        return parsedParam;
    }
}
