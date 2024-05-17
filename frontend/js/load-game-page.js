loadGame();

const cardGame = document.getElementById('card-game');
const gameTitle = document.getElementById('game-title');
const gameCard = document.getElementById('game-card');
const gameText = document.getElementById('game-text');
const backgroundHeroImage = document.getElementById('background-image-hero');
const gameVideo = document.getElementById('video-content');

async function loadGame() {
    const id = getUrlParam();
    fetch(`http://localhost:8080/games/${id}`)
    .then(response => response.json())
    .then(promise => createGamePage(promise));
}

function getUrlParam() {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    return urlParams.get('id');
}

function createGameCard(game) {
    return `
        <img src="${game.imgUrl}" />
        <div class="game-card-info">
            <div class="specs">
                <h6 class="card-text">Lançamento</h6>
                <p class="rounded-card-text">${game.releaseYear}</p>
            </div>
            <hr>
            <div class="specs">
                <h6 class="card-text">Gêneros</h6>
                <div class="specs-info" id="genre-specs"></div>
            </div>
            <hr>
            <div class="specs">
                <h6 class="card-text">Plataformas</h6>
                <div class="specs-info" id="platform-specs"></div>
            </div>
        </div>
    `
}

function createGameCardSpecs(game) {
    const genreSpecs = document.getElementById('genre-specs');
    const platformSpecs = document.getElementById('platform-specs');

    genreSpecs.innerHTML = "";
    platformSpecs.innerHTML = "";

    for (i = 0; i < Object.keys(game.genres).length; i++) {
        genreSpecs.innerHTML += `<p class="rounded-card-text">${game.genres[i].name}</p>`
    }

    for (i = 0; i < Object.keys(game.platforms).length; i++) {
        platformSpecs.innerHTML += `<p class="rounded-card-text">${game.platforms[i].name}</p>`
    }
}

function createGameVideo(game) {
    return `
        <iframe class="responsive-iframe-video" src="https://www.youtube.com/embed/${game.videoUrl}" allowfullscreen></iframe>
    `
}

function createGamePage(game) {
    gameTitle.innerHTML = game.name;
    gameCard.innerHTML = createGameCard(game);
    createGameCardSpecs(game);
    gameText.innerHTML = game.description;
    backgroundHeroImage.style.backgroundImage = `url('${game.backgroundImgUrl}')`;
    gameVideo.innerHTML = createGameVideo(game);
}