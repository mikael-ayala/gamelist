package com.gamelist.repositories;

import com.gamelist.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface GameRepository extends JpaRepository<Game, Long> {
    @Query(nativeQuery = true, value = """
            SELECT * FROM game
            INNER JOIN game_genre
            ON game.id = game_genre.game_id
            WHERE (:genreId IS NULL OR genre_id = :genreId)
            AND LOWER(name) LIKE CONCAT('%', LOWER(:name), '%')
            """)
    List<Game> searchByNameAndGenreId(String name, Long genreId);
}
