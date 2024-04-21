loadGames();

const loadButton = document.getElementById('load-button');
const gamesDiv = document.getElementById('games');
const cardGame = document.getElementById('card-game');

loadButton.addEventListener('click', () => {
    loadGames();
});

function loadGamePage(id) {
    window.location.href=`game.html?id=${id}`;
}

function createGameCard(game) {
    gamesDiv.innerHTML += `
        <div onclick="loadGamePage(${game.id})">
            <img src='${game.imgUrl}'>
            <p>${game.name}</p>
        </div>
    `
}

async function loadGames() {
    fetch('http://localhost:8080/games')
    .then(response => response.json())
    .then(promise => Promise.all(promise.map(game => createGameCard(game))));
}