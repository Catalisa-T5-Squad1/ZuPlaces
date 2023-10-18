const searchUserInput = document.getElementById('searchUser');
const userSuggestions = document.getElementById('userSuggestions');
let selectedUser = null;

searchUserInput.addEventListener('input', () => {
    const searchTerm = searchUserInput.value;

    // Limpe a lista suspensa
    userSuggestions.innerHTML = '';

    // Faça uma solicitação para buscar usuários conforme o usuário digita
    if (searchTerm.trim() !== '') {
        fetch(`http://localhost:8080/api/users/search?nome=${searchTerm}`)
            .then(response => response.json())
            .then(data => {
                data.forEach(user => {
                    const userOption = document.createElement('li');
                    userOption.classList.add('suggestion-item');

                    const userHeader = document.createElement('h3');
                    userHeader.style.color = 'black'; // Defina a cor preta
                    userHeader.innerHTML = `<strong>Usuário:</strong> ${user.name}`;
                    userOption.appendChild(userHeader);
                
                    userOption.addEventListener('click', () => {
                        searchUserInput.value = user.name;
                        selectedUser = user.name; // Armazene o usuário selecionado na variável selectedUser
                        console.log('Usuário selecionado:', selectedUser); // Imprima no console.log
                        userSuggestions.innerHTML = '';
                    });
                    userSuggestions.appendChild(userOption);
                });
            })
            .catch(error => {
                console.error(error);
            });
    }
});

userOption.addEventListener('click', () => {
    searchUserInput.value = user.nome;
    selectedUser = user.name;
    console.log('Usuário selecionado:', selectedUser);
    userSuggestions.innerHTML = '';
});

// Exemplo de como usar a variável selectedUser fora do evento de clique
// Se você precisar acessar selectedUser em outras partes do seu código, você pode fazê-lo aqui ou em outros eventos/funções.
// Por exemplo:
// Alguma função que é chamada após o usuário ter selecionado um usuário:
function processSelectedUser() {
    if (selectedUser) {
        console.log('Usuário selecionado:', selectedUser);
        // Faça alguma coisa com selectedUser...
    } else {
        console.log('Nenhum usuário selecionado.');
    }
}









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
