function buscarUsers() {
    fetch('http://localhost:8080/api/users')
        .then(response => response.json())
        .then(data => {
            const escolherUser = document.getElementById('escolher-user');

            escolherUser.innerHTML = '';

            const option = document.createElement('option');
            option.text = 'Escolha um User';
            escolherUser.appendChild(option);

            data.forEach(user => {
                const option = document.createElement('option');
                option.value = user.id;
                option.text = user.nome;
                escolherUser.appendChild(option);
            });
        })
        .catch(error => {
            console.error(error);
        });
}

buscarUsers();

document.getElementById('spaceForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const escolhido = document.getElementById('escolher-user').value;
    console.log('Usuário escolhido:', escolhido);
});




document.getElementById('spaceForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const formData = new FormData(this);
    const spaceData = {};

    formData.forEach((value, key) => {
        const keys = key.split('.');
        if (keys.length > 1) {
            if (!spaceData[keys[0]]) {
                spaceData[keys[0]] = {};
            }
            spaceData[keys[0]][keys[1]] = value;
        } else {
            spaceData[key] = value;
        }
    });

    fetch('http://localhost:8080/api/spaces', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(spaceData),
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            
            const mensagemSucesso = document.getElementById('mensagem-sucesso');
            mensagemSucesso.style.display = 'block';
        })
        .catch(error => {
            console.error(error);
        });
});

function buscarNomeUsuarioPorID() {
    const userID = document.getElementById('userIDInput').value;

    fetch(`http://localhost:8080/api/users/${userID}`)
        .then(response => response.json())
        .then(data => {
            const userResult = document.getElementById('userResult');
            userResult.innerHTML = `Nome do Usuário: ${data.nome}`; 
        })
        .catch(error => {
            const userResult = document.getElementById('userResult');
            userResult.innerHTML = `Usuário não encontrado ou ocorreu um erro: ${error}`;
        });
}
