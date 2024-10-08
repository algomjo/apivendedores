# API Vendedores

A API é responsável por gerenciar dados de vendedores, como matrícula, nome, data de nascimento, CPF ou CNPJ, e-mail, tipo de contratação e filial.

## Tecnologias Utilizadas

- Java 22
- Spring Boot
- Jakarta Persistence API (JPA)
- Spring Data JPA
- Mockito (com potencial substituição)
- JUnit 5 (versão 5.11.0-RC1)
- Swagger (Springfox 3.0.0)
- Spring Boot Actuator


## Como Executar:

- Clone o repositório.

- Execute `mvn clean install` para construir o projeto.

- Execute `mvn spring-boot:run` para iniciar a aplicação.

- Acesse [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) para ver a documentação da API.

## Estrutura do Projeto

```mermaid
classDiagram
    class Vendedor {
        +Long id
        +String matricula
        +String nome
        +LocalDate dataNascimento
        +String documento
        +String email
        +TipoContratacao tipoContratacao
        +Filial filial
    }
    
    class Filial {
        +Long id
        +String nome
        +String cnpj
        +String cidade
        +String uf
        +String tipo
        +boolean ativo
        +LocalDate dataCadastro
        +LocalDate ultimaAtualizacao
    }

    class TipoContratacao {
        <<enumeration>>
        OUTSOURCING
        CLT
        PJ
    }

    class VendedorRepository {
        +Optional~Vendedor~ findById(Long id)
        +List~Vendedor~ findAll()
        +Vendedor save(Vendedor vendedor)
        +void deleteById(Long id)
    }

    class VendedorService {
        +List~Vendedor~ findAll()
        +Optional~Vendedor~ findById(Long id)
        +Vendedor save(Vendedor vendedor)
        +void deleteById(Long id)
        +Filial getFilialById(Long id)
    }

    class VendedorController {
        +ResponseEntity~Vendedor~ createVendedor(Vendedor vendedor)
        +ResponseEntity~Vendedor~ getVendedorById(Long id)
        +ResponseEntity~List~Vendedor~~ getAllVendedores()
        +ResponseEntity~Void~ deleteVendedor(Long id)
    }

    Vendedor --> Filial
    Vendedor --> TipoContratacao
    VendedorService --> VendedorRepository
    VendedorController --> VendedorService
    Vendedor --> VendedorRepository


