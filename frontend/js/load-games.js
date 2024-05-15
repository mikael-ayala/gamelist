loadGames("", "0", "0");
loadGenres();
loadPlatforms();

const gamesCardsDiv = document.getElementById('cards-list');
const selectGenre = document.getElementById('select-genre');
const selectPlatform = document.getElementById('select-platform');
const searchGameForm = document.getElementById('search-game-form');

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

function createPlatformOption(platform) {
    selectPlatform.innerHTML += `
        <option value="${platform.id}">${platform.name}</option>
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
    .then(promise => Promise.all(promise.map(genre => createGenreOption(genre))));
}

async function loadPlatforms() {
    fetch('http://localhost:8080/platforms')
    .then(response => response.json())
    .then(promise => Promise.all(promise.map(platform => createPlatformOption(platform))));
}