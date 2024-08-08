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
        +tipoContratacao: String
        +filial: String
        +validarDocumento(): boolean
    }

    class VendedorRepository {
        +findByMatricula(matricula: String): Vendedor
        +findByDocumento(documento: String): Vendedor
    }

    class VendedorService {
        +salvar(vendedor: Vendedor): Vendedor
        +buscarPorMatricula(matricula: String): Vendedor
        +validarVendedor(vendedor: Vendedor): boolean
    }

    class VendedorController {
        +criarVendedor(vendedor: Vendedor): ResponseEntity<Vendedor>
        +buscarVendedor(matricula: String): ResponseEntity<Vendedor>
    }

    %% Definição das relações
    Vendedor --> VendedorRepository : usa
    VendedorService --> Vendedor : usa
    VendedorController --> VendedorService : usa
    VendedorService --> VendedorRepository : usa

    %% Definição da classe principal como ponto de entrada
    ApiVendedoresApplication --> VendedorController : inicializa

