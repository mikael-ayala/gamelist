INSERT INTO genre(id, name) VALUES (1, 'Plataforma');
INSERT INTO genre(id, name) VALUES (2, 'Moba');
INSERT INTO genre(id, name) VALUES (3, 'RPG');
INSERT INTO genre(id, name) VALUES (4, 'FPS');

INSERT INTO platform(id, name) VALUES (1, 'Nintendo');
INSERT INTO platform(id, name) VALUES (2, 'SEGA');

INSERT INTO game(id, name, description, release_year, img_url) VALUES (1, 'Super Mario Bros. 3', 'Super Mario Bros. 3 é um jogo eletrônico de plataforma da série Super Mario, desenvolvido pela Nintendo Entertainment Analysis & Development e publicado pela Nintendo para o console Nintendo Entertainment System (NES). Foi lançado no Japão em 23 de outubro de 1988, na América do Norte em 12 de fevereiro de 1990 e na Europa em 29 de agosto de 1991, e acompanha as aventuras dos encanadores Mario e Luigi que buscam resgatar a Princesa Cogumelo e os governantes dos sete reinos do antagonista Bowser.', 1988, 'https://upload.wikimedia.org/wikipedia/pt/a/a5/Super_Mario_Bros._3_coverart.png');
INSERT INTO game(id, name, description, release_year, img_url) VALUES (2, 'Sonic the Hedgehog 2', 'Sonic the Hedgehog 2 é um jogo eletrônico de plataforma desenvolvido pela Sega Technical Institute e publicado pela Sega para o Mega Drive. É o segundo jogo da série Sonic the Hedgehog para o referido console. Introduziu o amigo do protagonista, Miles "Tails" Prower, controlável por um segundo jogador. Na história, Sonic e Tails devem parar o antagonista da série, o Dr. Eggman, de roubar as Esmeraldas do Caos para alimentar sua estação espacial, o Death Egg.', 1992, 'https://upload.wikimedia.org/wikipedia/pt/thumb/1/1c/Sonic_the_Hedgehog_2_capa.png/270px-Sonic_the_Hedgehog_2_capa.png');

INSERT INTO game_genre(game_id, genre_id) VALUES (1, 1);
INSERT INTO game_genre(game_id, genre_id) VALUES (2, 1);

INSERT INTO game_platform(game_id, platform_id) VALUES (1, 1);
INSERT INTO game_platform(game_id, platform_id) VALUES (2, 2);