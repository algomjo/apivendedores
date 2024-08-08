package casasbahia.apivendedores.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity

public class Filial {
    /*private String id;
    private String nome;
    private String cnpj;
    private String cidade;
    private String uf;
    private String tipo;
    private boolean ativo;
    private LocalDate dataCadastro;
    private LocalDate ultimaAtualizacao;*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, message = "Nome da filial deve ter pelo menos 2 caracteres")
    private String nome;

    @NotNull
    @Pattern(regexp = "\\d{14}", message = "CNPJ inválido")
    private String cnpj;

    @NotNull
    @Size(min = 2, message = "Cidade deve ter pelo menos 2 caracteres")
    private String cidade;

    @NotNull
    @Size(min = 2, max = 2, message = "UF deve ter 2 caracteres")
    private String uf;

    @NotNull
    private String tipo;

    @NotNull
    private boolean ativo;

    private LocalDate dataCadastro;
    private LocalDate ultimaAtualizacao;

    // Construtores
    public Filial() {}

    public Filial(Long id, String nome, String cnpj, String cidade, String uf, String tipo, boolean ativo, LocalDate dataCadastro, LocalDate ultimaAtualizacao) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.uf = uf;
        this.tipo = tipo;
        this.ativo = ativo;
        this.dataCadastro = dataCadastro;
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDate getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(LocalDate ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }
}
