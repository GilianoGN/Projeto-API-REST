# PROJETO API-RESTful
Projeto RESTful API com Spring Boot, Java e Railway


## Principais Tecnologias
* VS Code 1.99.3: IDE de editor de código-fonte desenvolvido pela Microsoft para Windows, Linux e macOS. 
* Java 21: Linguagem multiplataforma, orientada a objetos e centrada em rede
* Maven 4.0.0 (3.11.0 para rodar no Railway): Ferramenta de automação de compilação
* Spring Boot 3.4.5: Ferramenta open source que simplifica a utilização de estruturas baseadas em Java
* Spring Data JPA: Framework de programação dentro do Spring Framework para acesso e manipulação de dados.
* OpenAPI 2.8.6 (Swagger): Especificação para descrever interfaces de API REST
* Railway: Plataforma baseada na nuvem que visa tornar o processo de desenvolvimento mais simples e acessível

## Diagrama de Classes

```mermaid
classDiagram
  class User {
    -String name
    -Account account
    -Feature[] features
    -Card card
    -News[] news
  }
  class Account {
    -String number
    -String agency
    -Number balance
    -Number limit
  }
  class Feature {
    -String icon
    -String description
  }
  class Card {
    -String number
    -Number limit
  }
  class News {
    -String icon
    -String description
  }
  User "1" *-- "1" Account 
  User "1" *-- "n" Feature 
  User "1" *-- "n" Card 
  User "1" *-- "n" News 
```
