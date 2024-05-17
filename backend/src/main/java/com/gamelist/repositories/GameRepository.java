package com.gamelist.repositories;

import com.gamelist.entities.Game;
import com.gamelist.projections.GameMinProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface GameRepository extends JpaRepository<Game, Long> {
    @Query(nativeQuery = true, value = """
            SELECT DISTINCT id, name, img_url AS image FROM game
            INNER JOIN game_genre
            ON game.id = game_genre.game_id
            INNER JOIN game_platform
            ON game.id = game_platform.game_id
            WHERE (:genreId IS NULL OR genre_id = :genreId)
            AND (:platformId IS NULL OR platform_id = :platformId)
            AND LOWER(name) LIKE CONCAT('%', LOWER(:name), '%')
            """)
    List<GameMinProjection> searchByNameAndGenreIdAndPlatormId(String name, Long genreId, Long platformId);
}
