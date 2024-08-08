## Diagrama de Classes

```mermaid
classDiagram
    %% Definição das classes
    class ApiVendedoresApplication {
        +main(args: String[]): void
    }

    class Vendedor {
        +matricula: String
        +nome: String
        +dataNascimento: LocalDate
        +documento: String
        +email: String
        +tipoContratacao: TipoContratacao
        +filial: String
        +validarDocumento(): boolean
    }

    class VendedorRepository {
        +findByMatricula(matricula: String): Optional~Vendedor~
        +findByDocumento(documento: String): Optional~Vendedor~
        +save(vendedor: Vendedor): Vendedor
        +deleteById(id: Long): void
    }

    class VendedorService {
        +findAll(): List~Vendedor~
        +findById(id: Long): Optional~Vendedor~
        +save(vendedor: Vendedor): Vendedor
        +deleteById(id: Long): void
        +generateMatricula(tipoContratacao: TipoContratacao): String
    }

    class VendedorController {
        +getAllVendedores(): List~Vendedor~
        +getVendedorById(id: Long): ResponseEntity~Vendedor~
        +createVendedor(vendedor: Vendedor): ResponseEntity~Vendedor~
        +updateVendedor(id: Long, vendedor: Vendedor): ResponseEntity~Vendedor~
        +deleteVendedor(id: Long): ResponseEntity~void~
    }

    class TipoContratacao {
        <<enumeration>>
        CLT
        PJ
        OUTSOURCING
    }

    %% Definição das relações
    Vendedor --> VendedorRepository : usa
    VendedorService --> Vendedor : usa
    VendedorController --> VendedorService : usa
    VendedorService --> VendedorRepository : usa
    Vendedor --> TipoContratacao : contém

    %% Definição da classe principal como ponto de entrada
    ApiVendedoresApplication --> VendedorController : inicializa
