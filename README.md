


🖥️ Tecnologias Utilizadas
* `Java 11` - Linguagem de programação
* `Spring Boot (2.7.15)` - Framework para criação de aplicativos Java
* `Spring Boot Data JPA` - Facilita o acesso a bancos de dados relacionais.
* `Spring Boot Validation` - Biblioteca que ajuda na validação de entrada de dados em aplicativos Spring Boot.
* `Spring Boot Web` - Facilita o desenvolvimento de aplicativos da web usando o Spring Boot.
* `H2 Database (Runtime)` - Um banco de dados SQL leve e embutido que é executado em tempo de execução.
* `PostgreSQL` - Um robusto banco de dados.
* `Lombok` - Uma biblioteca Java que ajuda a reduzir a verbosidade do código.
* `Springdoc OpenAPI UI (1.7.0)` -  Uma ferramenta que gera automaticamente a documentação da API com base nas anotações do Spring.
* `Junit (4.12)` -  Uma ferramenta para teste unitários.
* `Modelmapper (2.4.4)` -  Ferramenta para fazer o mapeamento entre model e DTO.
* `Gson (2.8.8)` -  Ferramenta para converter JSON para objeto ou objeto em JSON.
* `HTML` - Linguagem de marcação para estruturar o conteúdo da página.
* `CSS` - Linguagem de estilização para dar estilo às páginas.
* `JavaScript` - Linguagem de programação para interatividade do usuário.




## ⚙️ Como Executar a Aplicação

1. **Pré-requisitos:**
    - Certifique-se de ter o [JDK 11](https://www.oracle.com/java/technologies/downloads/#java11) instalado em seu computador.

2. **Clone o Repositório:**
    - Faça o clone do repositório do projeto para o seu ambiente de desenvolvimento.

3. **Navegue até o Diretório:**
    - Abra o terminal e navegue até o diretório onde se encontra o arquivo `Application.java`.

4. **Compilação:**
    - Compile o arquivo utilizando o seguinte comando:
      ```
      javac Application.java
      ```

5. **Execução:**
    - Após compilar, execute a aplicação com o seguinte comando:
      ```
      java Application.java
      ```

---

### 🚀 Como Executar o Frontend

1. Abra o arquivo `index.html` no seu navegador web.

Você precisa ir até src/main/resources/


### 📚 Documentação com Swagger

A documentação da API é gerada automaticamente pelo Swagger, facilitando a compreensão e teste das suas rotas. Siga os passos abaixo para acessar a documentação:

1. Certifique-se de que a aplicação esteja em execução.
2. Abra um navegador da web.
3. Acesse o seguinte link: [http://localhost:8080/swagger-ui/index.html#](http://localhost:8080/swagger-ui/index.html#).
4. Na página do Swagger, você verá uma lista de todas as operações disponíveis na sua API, incluindo os detalhes de entrada e saída para cada rota.
5. Clique em uma operação para abrir seus detalhes. Aqui, você pode testar a rota diretamente no navegador, fornecendo os parâmetros necessários e clicando em "Try it out!".

Certifique-se de que a aplicação esteja em execução para que o Swagger possa gerar a documentação corretamente. A documentação do Swagger é uma ferramenta valiosa para desenvolvedores e usuários da API, permitindo entender e testar facilmente as funcionalidades disponíveis.

---


## 🏢 Banco de Dados em Memória H2

A aplicação utiliza o banco de dados em memória H2 para armazenar os dados. Para acessar o console de administração do H2, siga os passos abaixo:

1. Acesse [http://localhost:8080/h2-console](http://localhost:8080/h2-console) no seu navegador.
2. No campo "JDBC URL", coloque `jdbc:h2:mem:dbtaxEasy` (que é a URL de conexão com o banco de dados H2 em memória).
3. No campo "Username", insira `root`.
4. No campo "Password", insira `admin123`.
5. Clique em "Connect" para acessar o console de administração do H2.

Lembre-se de que o banco de dados em memória H2 é reiniciado sempre que a aplicação é reiniciada.

---

### 🐳 Como executar o docker

- Necessário ter docker instalado em sua máquina.
- Você precisará ter o [Docker](https://www.docker.com/products/docker-desktop/) instalado no seu computador;
- Abra o terminal e navegue até a raiz do projeto;
- Execute o comando abaixo, para criar o banco de dados chamado stackspot, com usuário postgres e password, password.

```
docker-compose up
```

---

👨‍💻 Autores

Nome: Guilherme Januário <br>
Linkedin: https://www.linkedin.com/in/guilherme-janu%C3%A1rio/ <br> 
GitHub: https://github.com/guiijanuario

Nome: Ricardo dos Santos <br>
Linkedin: https://www.linkedin.com/in/ricardo-dos-santos-18006a239/ <br>
GitHub: https://github.com/RickZup <br>

Nome: Athos Caetano da Silva <br>
Linkedin: https://www.linkedin.com/in/athos-caetano-da-silva-26722020b/ <br>
GitHub: https://github.com/AthosDeveloper