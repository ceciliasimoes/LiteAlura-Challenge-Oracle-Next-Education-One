# 📚 Projeto LiterAlura 📚

## Descrição

O projeto **LiterAlura** é um catálogo de livros interativo que permite aos usuários buscar livros através de uma API específica, armazenar as informações em um banco de dados e realizar várias consultas para filtrar e exibir os livros e autores de interesse. Este projeto foi desenvolvido em Java utilizando Spring Boot para gerenciar as operações de backend e interações com o banco de dados.

## Funcionalidades 🚀

### Menu de Interação

O sistema oferece um menu interativo com as seguintes opções:

1. **🔍 Buscar livro pelo Título**: Permite ao usuário buscar livros por título e exibir os detalhes do primeiro resultado encontrado.
2. **📚 Listar livros registrados**: Lista todos os livros armazenados no banco de dados.
3. **🧑‍🎨 Listar Autores Registrados**: Lista todos os autores registrados no banco de dados.
4. **📅 Listar Autores vivos em algum ano**: Lista autores que estavam vivos em um determinado ano.
5. **🌐 Listar livros em um idioma específico**: Permite ao usuário listar livros por idioma.
6. **🎂 Listar Autores por ano de nascimento**: Lista autores nascidos em um determinado ano.
7. **🏆 Top 10 Livros**: Lista os 10 livros mais baixados.
8. **🔎 Buscar Autor por nome**: Permite ao usuário buscar autores por nome.

### Consumo da API 🌐

O sistema realiza requisições a uma API de livros (Gutendex) para obter informações sobre os livros solicitados pelos usuários.

### Armazenamento de Dados 💾

Os dados obtidos da API são armazenados em um banco de dados PostgreSQL. As entidades principais são `Livro` e `Autor`.

### Consultas e Filtragem 🔍

O sistema permite realizar várias consultas e filtragens nos dados armazenados, como buscar livros por título, listar livros por idioma, listar autores por ano de nascimento, etc.

## Tecnologias Utilizadas 🛠️

- **Java**: Linguagem de programação principal.
- **Spring Boot**: Framework utilizado para simplificar o desenvolvimento de aplicações Java.
- **Hibernate**: Framework de mapeamento objeto-relacional (ORM) utilizado para interação com o banco de dados.
- **PostgreSQL**: Banco de dados relacional utilizado para armazenar os dados.
- **Jackson**: Biblioteca utilizada para processar JSON.

## Estrutura do Projeto 🗂️

- **Models**: Contém as classes de entidade (`Livro` e `Autor`) que representam as tabelas do banco de dados.
- **DTOs**: Contém as classes de Data Transfer Object utilizadas para transportar dados entre diferentes camadas da aplicação.
- **Repositories**: Contém as interfaces de repositório que estendem `JpaRepository` para realizar operações CRUD.
- **Services**: Contém as classes de serviço que implementam a lógica de negócio da aplicação.
- **Principal**: Contém a classe principal `Main` que implementa o menu interativo e coordena as operações do sistema.

## Execução do Projeto ▶️

1. Clone o repositório para sua máquina local.
2. Configure o banco de dados PostgreSQL e atualize as configurações de conexão no arquivo `application.properties`.
3. Compile e execute o projeto usando sua IDE ou via linha de comando.
4. Interaja com o menu para realizar as operações disponíveis.

## Contato 📧

Para dúvidas ou mais informações, entre em contato pelo meu perfil aqui 😊.

---
