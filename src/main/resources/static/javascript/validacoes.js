const formulario = document.querySelector('.form form');
const inputsAll = document.querySelectorAll('form input:not(#rua, #complemento, #bairro, #cidade)')
const senha = document.getElementById('senha');
const confirmarSenha = document.getElementById('confirmar-senha');
const strong =  /(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9])(?=.{8,})/
const midium = / ((?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9])(?=.{6,}))|((?=.*[a-z])(?=.*[A-Z])(?=.*[^A-Za-z0-9])(?=.{8,}))/

formulario.addEventListener('submit', function (e){
    inputsAll.forEach(input => {
            if(input.value == ''){
                e.preventDefault();
                document.querySelector('.err-'+input.id).classList.remove('invisible')
            } else {
                document.querySelector('.err-'+input.id).classList.add('invisible')
            }
        });

    if(!senha.value.match(strong)){
        e.preventDefault();
        senha.focus();
    }

    if(senha.value != confirmarSenha.value){
        e.preventDefault();
        alert('As senhas não são iguais')
    }
});

senha.oninput = function(){
    if(senha.value.match(strong)){
        senha.style.outline = '1px solid green'
    }else if (senha.value.match(midium)){
        senha.style.outline = '1px solid yellow'
    }else {
        senha.style.outline = '1px solid red'
    }
}


