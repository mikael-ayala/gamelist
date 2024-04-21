loadGame();

const cardGame = document.getElementById('card-game');

async function loadGame() {
    const id = getUrlParam();
    fetch(`http://localhost:8080/games/${id}`)
    .then(response => response.json())
    .then(promise => createCardGame(promise));
}

function getUrlParam() {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    return urlParams.get('id');
}

function createCardGame(game) {
    cardGame.innerHTML = game.name;
}