# Desafio Técnico | Software Development III (Java)

## Construção de API REST para cadastro, consulta e atualização (CRUD)

De acordo com o teste passado, foi definido utilizar a linguage JAVA 1.8 juntamente com o framework Spring Boot por questão de afinidade com estes mencioandos.

Além da linguagem e framework, também foram utilizadas as seguintes tecnologias:
- MAVEN
- Banco de Dados H2 (em memória)
- Swagger 2
- Json Schema Validator (org.everit.json)
- Lombok
- Jacoco (Relatório de cobertura de testes)

## Design da API
Para a arquitetura da API, foi utilizado o padrão MVC (Model, View e Controller).

Este padrão é utilizado globalmente para facilitar a manutenção e entendimento do código.

## Uso da API
Para este projeto, foram disponibilizados 3 endpoints:
- https://test-java-dock.herokuapp.com/v1/terminal
- - VERBO POST (Responsável por cadastrar uma entidade do tipo `terminal`)
- https://test-java-dock.herokuapp.com/v1/terminal/44332211
- - VERBO GET (Responsável por consultar uma entidade do tipo `terminal` já cadastrada)
- https://test-java-dock.herokuapp.com/v1/terminal/44332211
- - VERBO PUT (Responsável por atualizar uma entidade do tipo `terminal` já cadastrada)

Para mais informações de uso, verificar a documentação abaixo.

## Documentação do Projeto
Toda a documentação do projeto pode ser acessada pelo [SWAGGER](https://test-java-dock.herokuapp.com/swagger-ui.html).

Obs.: Como o projeto está hospedado em uma plataforma gratuita, a aplicação é hibernada após 30 minutos sem uso e pode haver certo delay para voltar a ser utilizada

## Testes
A fim de melhorar a qualidade da entrega e garantir que todos os serviços estão funcionais, foram feitos testes unitários e integrados.
Todas as linhas de código do projeto foram testadas e podem ser validadas através do relatório gerado pelo `Jacoco`.

Para gerar tal relatório, basta realizar o clone do projeto e rodar o comando `mvn clean install` e acessar o arquivo gerado no diretório `target/site/jacoco/index.html`

## Docker
Como o objetivo do Spring é facilitar a criação de uma aplicação REST, por que não facilitar também seu uso?
Pensando nisso, foi criado o arquivo `Dockerfile` para geração da imagem docker da aplicação.
Para criar e testar a imagem docker, basta executar os comandos:
- `docker build -t dock .`
- `docker run -p 8080:8080 dock`

### A aplicação está disponibilizada no HEROKU e pode ser acessada publicamente através do endereço [DOCK](https://test-java-dock.herokuapp.com/v1/terminal/44332211)