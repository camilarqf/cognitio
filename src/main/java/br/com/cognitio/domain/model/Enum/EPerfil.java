package br.com.cognitio.domain.model.Enum;

public enum EPerfil {
    ADMIN(0, "ROLE_ADMIN"),
    MODERADOR(1, "ROLE_MODERADOR"),
    ALUNO(2, "ROLE_ALUNO");

    private Integer codigo;
    private String descricao;

    EPerfil(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }




}
