# API REST ZupPlaces

Este projeto é uma API REST que te permite buscar informações de espaços gratuitos mais próximos. O objetivo é fazer com que pessoas conectem-se com esses espaços através de informações disponibilizados seja por uma empresa ou por uma pessoa voluntária que cedeu algum local para ser compartilhado por outros. 
Essa API tem como objetivo auxiliar pessoas carentes ou de baixa renda que normalmente não conseguiria ter acesso a esses espaços encontrar lugares que lhes ofereçam desde alimentos a uma biblioteca pública, Impactando a sociedade como um todo. 
O projeto possui as seguintes entidades: 
Usuário: Representa o usuário final que terá acesso a API 
 atributos: id, nome, email, senha, Tipo de Pessoa(Se é pessoa física ou jurídica), telefone e tipo de documento (se os números contidos são CPF ou CNPJ)
Espaço: Representa o espaço que o usuário terá acesso 
Atributos: id, nome, Usuário (Usuário associado ao espaço pelo id, sendo o relacionamento muitos espaços para um usuário), recurso (espaço associado a um recurso pelo id, sendo muitos espaços para um recurso), endereço (espaço associado a um endereço pelo id, sendo um espaço para um endereço) Horário de abertura (aqui é definido quando o espaço será aberto e quando será fechado), descrição (aqui define meios para acessar esses espaços, um exemplo seria quantos pontos de wifi disponíveis e como acessá-los)  distância.
Recurso: Representa o recurso disponibilizado pelo espaço 
Atributos: id, nome (determinar o recurso disponibilizado pelo espaço como água potável por exemplo), espaços (um recurso associado avários espaços) 
Contato: Representa o contato da pessoa que está informando o endereço do local 
Atributos: id, nome (nome da pessoa disponibilizando o espaço), email (email da pessoa disponibilizando o email para contato), telefone (telefone da pessoa que disponibilizou informação para contato), descrição (informação de qual local essa pessoa está indicando esse espaço), usuário(contato sendo associado a um usuário, relacionamento um usuário para um contato, exemplo USP), espaço (contato sendo associado a um espaço, relacionamento um contato para um espaço exemplo biblioteca bloco a) 
endereço: representa o endereço que está associado ao espaço informado
atributos: 
id, cep, logradouro (nome da rua), numeroEndereço (numero do endereço exemplo 30), complemento (exemplo casa), bairro (exemplo quintamariana), localidade (exemplo cidade sp), uf (estado RJ) latitude, longitude