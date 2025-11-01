 Sistema de Gerenciamento de Veículos - Garagem

Este projeto foi desenvolvido como parte do módulo de Programação Orientada a Objetos (POO), com o objetivo de aplicar os conceitos de herança, encapsulamento, abstração e polimorfismo dentro de uma aplicação prática utilizando Java + Spring Boot + MySQL + Thymeleaf (MVC).

Objetivo do Projeto:

Criar um sistema de gerenciamento de veículos que permita:

Cadastrar novas marcas, modelos e veículos.

Editar e excluir informações existentes.

Visualizar os dados cadastrados de forma organizada.

Interagir com um banco de dados MySQL real, aplicando o padrão MVC (Model-View-Controller).

Estrutura do Projeto:

A aplicação segue a arquitetura MVC, dividida em pacotes com responsabilidades bem definidas:

src/main/java/com/vini/garagem
│
├── domain/        → Contém as classes de modelo (Brand, Model, Vehicle)
├── repo/          → Interfaces de repositórios JPA (BrandRepository, ModelRepository, VehicleRepository)
├── service/       → Camada de regras de negócio (BrandService, ModelService, VehicleService)
└── web/           → Controladores responsáveis pelas páginas HTML (BrandController, ModelController, VehicleController)

E dentro de resources/templates ficam as páginas da interface com o usuário:
src/main/resources/templates/
│
├── vehicles.html
└── vehicle-form.html

Tecnologia utilizadas

| Categoria      | Tecnologia            |
| -------------- | --------------------- |
| Linguagem      | Java 17               |
| Framework      | Spring Boot 3.5.7     |
| Banco de Dados | MySQL 8.0             |
| Interface      | Thymeleaf (HTML, CSS) |
| Build Tool     | Maven                 |
| IDE            | IntelliJ IDEA         |

Conceitos de POO Aplicados
--- Encapsulamento ---

Cada classe possui seus atributos privados, acessados apenas por meio de getters e setters, protegendo os dados.

--- Herança ---

As classes compartilham características em comum — por exemplo, Model e Vehicle podem se relacionar com Brand.

--- Abstração ---

A lógica do sistema está separada da interface (HTML), simplificando a manutenção do código.

--- Polimorfismo ---

Os métodos podem ser sobrescritos e aplicados de diferentes formas de acordo com o contexto da classe.

Banco de Dados

O projeto utiliza o banco MySQL, configurado no arquivo application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/garagem
spring.datasource.username=root
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

O banco cria automaticamente as tabelas:

brand
model
vehicle

---- Interface do Sistema ----
- Página principal (/vehicles)

Exibe todos os veículos cadastrados com opções de editar e excluir.

- Página de cadastro (/vehicles/new)

Permite inserir um novo veículo com os campos:

Marca
Modelo
Categoria
Ano
Cor
Preço
Quilometragem
Status (Disponível / Vendido)


️ Como Executar o Projeto

Clone o repositório: git clone https://github.com/SEU-USUARIO/Garagem.git
Abra no IntelliJ IDEA

Configure o banco MySQL

Crie um banco de dados chamado garagem

Ajuste username e password no arquivo application.properties

Execute o projeto

Rode o arquivo GaragemApplication.java

Acesse no navegador:http://localhost:8080/vehicles

Autor

Vinícius Prates
📧 viniciusprates12@gmail.com

🧠 Projeto desenvolvido para disciplina de Programação Orientada a Objetos - UniFECAF
