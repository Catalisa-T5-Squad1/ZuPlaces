const searchUserInput = document.getElementById('searchUser');
const userSuggestions = document.getElementById('userSuggestions');
let selectedUser = {
    id: null,
    name: null
};

searchUserInput.addEventListener('input', () => {
    const searchTerm = searchUserInput.value;

    userSuggestions.innerHTML = '';

    if (searchTerm.trim() !== '') {
        fetch(`http://localhost:8080/api/users/search?nome=${searchTerm}`)
            .then(response => response.json())
            .then(data => {
                data.forEach(user => {
                    const userOption = document.createElement('li');
                    userOption.classList.add('suggestion-item');

                    const userHeader = document.createElement('h3');
                    userHeader.style.color = 'black'; 
                    userHeader.innerHTML = `<strong>Usuário:</strong> ${user.name}`;
                    userOption.appendChild(userHeader);
                
                    userOption.addEventListener('click', () => {
                        searchUserInput.value = user.name;
                        selectedUser.id = user.id;  // Salvar o ID do usuário selecionado
                        selectedUser.name = user.name;  // Salvar o nome do usuário selecionado
                        console.log('Usuário selecionado:', selectedUser);
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

// userOption.addEventListener('click', () => {
//     searchUserInput.value = user.nome;
//     selectedUser = user.name;
//     console.log('Usuário selecionado:', selectedUser);
//     userSuggestions.innerHTML = '';
// });


function processSelectedUser() {
    if (selectedUser) {
        console.log('Usuário selecionado:', selectedUser);
    } else {
        console.log('Nenhum usuário selecionado.');
    }
}

const resourcedSelected = processSelectedResource();

console.log("Estou aqui no cadastro space, será que deu bom? : -> " +  resourcedSelected);

const spaceForm = document.getElementById('spaceForm');

spaceForm.addEventListener('submit', (event) => {
    event.preventDefault();

    const formData = new FormData(spaceForm);

    const spaceData = {
        nome: formData.get('nome'),
        usuario_id: selectedUser.id, // Certifique-se de ajustar o nome do campo de usuário.
        recurso_id: selectedResource.id, // Certifique-se de ajustar o nome do campo de recurso.
        endereco: {
            cep: formData.get('endereco.cep'),
            numero_endereco: formData.get('endereco.numero_endereco'),
            complemento: formData.get('endereco.complemento')
        },
        horario_funcionamento: formData.get('horario_funcionamento'),
        descricao_espaco: formData.get('descricao_espaco')
    };

    console.log("o spaceData é: " + spaceData);

    // Enviar os dados do espaço para o servidor via POST
    fetch('http://localhost:8080/api/spaces', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(spaceData),
    })
        .then(response => {
            if (response.status === 201) {
                // Espaço criado com sucesso
                document.getElementById('mensagem-sucesso').style.display = 'block';
            } else {
                // Lidar com erros
                console.error('Erro ao criar espaço:', response.statusText);
            }
        })
        .catch(error => {
            console.error('Erro ao criar espaço:', error);
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
