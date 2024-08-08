package casasbahia.apivendedores.model;

public enum TipoContratacao {
    OUTSOURCING("Outsourcing"),
    CLT("CLT"),
    PESSOA_JURIDICA("Pessoa Jurídica");

    private final String descricao;

    TipoContratacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
