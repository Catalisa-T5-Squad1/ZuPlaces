document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('cadastroUsuarioForm');
    const cpfField = document.getElementById('documento');
    const typePerson = "";

    form.addEventListener('submit', function(event) {
      event.preventDefault();

      // if(parseInt(cpfField.value) === 11) {
      //   typePerson = "PHYSICAL_PERSON"
      //   console.log(typePerson)
      // } else {
      //   console.log(cpfField.value)
      //   alert("o CPF deve possuir apenas 11 caracteres e não deve ter pontos")
      //   return;
      // }

      // if(cpfField.value == 14) {
      //   typePerson = "LEGAL_PERSON"
      // } else {
      //   alert("o CNPJ deve possuir apenas 14 caracteres e não deve ter pontos")
      //   return;
      // }
    
      const nome = document.getElementById('nome').value;
      const cpf = cpfField.value;
      const telefone = document.getElementById('telefone').value;
      const email = document.getElementById('login').value;
      const senha = document.getElementById('senhaCadastro').value;
      const confirmarSenha = document.getElementById('confirmarSenhaCadastro').value;
    
      if (senha !== confirmarSenha) {
        alert('As senhas não correspondem');
        return;
      }
  
      const usuarioData = {
        nome: nome,
        email: email,
        senha: senha,
        tipo_pessoa: "PHYSICAL_PERSON",
        telefone: telefone,
        documento_identificacao: cpf,
      };
    
      fetch('http://localhost:8080/api/users', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(usuarioData),
      })
        .then(response => response.json())
        .then(usuarioData => {
          console.log(usuarioData);
          if (usuarioData) {
            alert('Cadastro realizado com sucesso!');
            form.reset();
          } else {
            alert('Ocorreu um erro durante o cadastro. Tente novamente mais tarde.');
          }
        })
        .catch(error => {
          console.error('Erro ao realizar o cadastro: ' + error);
          alert('Ocorreu um erro durante o cadastro. Tente novamente mais tarde.');
        });
    });
  });


  