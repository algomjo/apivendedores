package casasbahia.apivendedores.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vendedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotNull
    @Pattern(regexp = "\\d{8}-\\b(OUT|CLT|PJ)\\b", message = "Matrícula deve seguir o padrão 98767367-OUT, 98767367-CLT ou 98767367-PJ")
    private String matricula;

    @NotNull
    @Size(min = 2, message = "Nome deve ter pelo menos 2 caracteres")
    private String nome;

    private LocalDate dataNascimento;

    @NotNull
    @Pattern(regexp = "(\\d{11}|\\d{14})", message = "CPF ou CNPJ inválido")
    private String documento;

    @NotNull
    @Email(message = "E-mail inválido")
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoContratacao tipoContratacao;

    @NotNull
    private String filial;

    public boolean validarDocumento() {
        if (tipoContratacao == TipoContratacao.CLT || tipoContratacao == TipoContratacao.OUTSOURCING) {
            // CPF deve ter 11 dígitos
            return documento != null && documento.matches("\\d{11}") && validarCPF(documento);
        } else if (tipoContratacao == TipoContratacao.PESSOA_JURIDICA) {
            // CNPJ deve ter 14 dígitos
            return documento != null && documento.matches("\\d{14}") && validarCNPJ(documento);
        }
        return false;
    }

    private boolean validarCPF(String cpf) {
        // Implementação simples para validação de CPF
        return cpf.chars().distinct().count() > 1; // Exemplo: não pode ter todos os dígitos iguais
    }

    private boolean validarCNPJ(String cnpj) {
        return cnpj.chars().distinct().count() > 1; // Exemplo: não pode ter todos os dígitos iguais
    }
}
