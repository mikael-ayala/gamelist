loadGames("", "0", "0");
loadGenres();
loadPlatforms();

const gamesCardsDiv = document.getElementById('cards-list');
const selectGenre = document.getElementById('select-genre');
const selectPlatform = document.getElementById('select-platform');
const searchGameForm = document.getElementById('search-game-form');

const checkboxPlatform = document.getElementById('checkbox-platforms');
const checkboxGenre = document.getElementById('checkbox-genres');
const insertGameForm = document.getElementById('insert-form');

insertGameForm.addEventListener('submit', (e) => {
    e.preventDefault();

    const formData = new FormData(e.target);

    let nome = document.getElementById('nome').value;
    let description = document.getElementById('descricao').value;
    let releaseYear = document.getElementById('ano').value;
    let imgUrl = document.getElementById('img').value;
    let backgroundImgUrl = document.getElementById('bg_img').value;
    let videoUrl = document.getElementById('video').value;

    const data = {};
    data.name = nome;
    data.description = description;
    data.imgUrl = imgUrl;
    data.releaseYear = releaseYear;
    data.backgroundImgUrl = backgroundImgUrl;
    data.videoUrl = videoUrl;

    genresArray = formData.getAll('genres');
    platformsArray = formData.getAll('platforms');

    genres = []
    for (const id of genresArray) {
        genres.push({id: id})
    }

    platforms = []
    for (const id of platformsArray) {
        platforms.push({id: id})
    }

    data.genres = genres;
    data.platforms = platforms;

    JSON.stringify(data);

    console.log(JSON.stringify(data));

    fetch('http://localhost:8080/games', {
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(json => createGameCard(json))
    .then(() => {
        gamesCardsDiv.innerHTML = '';
        loadGames("", "0", "0");
        document.getElementById('nome').value = '';
        document.getElementById('descricao').value = '';
        document.getElementById('ano').value = '';
        document.getElementById('img').value = '';
        document.getElementById('bg_img').value = '';
        document.getElementById('video').value = '';
    });
});

searchGameForm.addEventListener('submit', (e) => {
    e.preventDefault();
    gamesCardsDiv.innerHTML = "";
    let name = document.getElementById('game-name').value;
    let genre = document.getElementById('select-genre').value;
    let platform = document.getElementById('select-platform').value;
    loadGames(name, genre, platform);
});

function loadGamePage(id) {
    window.location.href=`game.html?id=${id}`;
}

function createGameCard(game) {
    console.log(game);

    gamesCardsDiv.innerHTML += `
        <div class="card" onclick="loadGamePage(${game.id})">
            <img src="${game.imgUrl}">
            <div class="text-card">
                <p>${game.name}</p>
            </div>
        </div>
    `
}

function createGenreOption(genre) {
    selectGenre.innerHTML += `
        <option value="${genre.id}">${genre.name}</option>
    `
}

function createGenreCheckbox(genre) {
    checkboxGenre.innerHTML += `
        <div>
            <input type="checkbox" id="${genre.name}" name="genres" value="${genre.id}" />
            <label for="${genre.name}">${genre.name}</label>
        </div>
    `
}

function createPlatformOption(platform) {
    selectPlatform.innerHTML += `
        <option value="${platform.id}">${platform.name}</option>
    `
}

function createPlatformCheckbox(platform) {
    checkboxPlatform.innerHTML += `
        <div>
            <input type="checkbox" id="${platform.name}" name="platforms" value="${platform.id}" />
            <label for="${platform.name}">${platform.name}</label>
        </div>
    `
}

async function loadGames(name, genre, platform) {
    fetch(`http://localhost:8080/games?name=${name}&genreId=${genre}&platformId=${platform}`)
    .then(response => response.json())
    .then(promise => Promise.all(promise.map(game => createGameCard(game))));
}

async function loadGenres() {
    fetch('http://localhost:8080/genres')
    .then(response => response.json())
    .then(promise => Promise.all(promise.map(genre => {
        createGenreOption(genre)
        createGenreCheckbox(genre)
    })));
}

async function loadPlatforms() {
    fetch('http://localhost:8080/platforms')
    .then(response => response.json())
    .then(promise => Promise.all(promise.map(platform => {
        createPlatformOption(platform)
        createPlatformCheckbox(platform)
    })));
}