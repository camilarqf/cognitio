package br.com.cognitio.domain.model;

import br.com.cognitio.domain.model.Enum.EPerfil;

import java.time.LocalDate;

public class User {
    private Long id;
    private String login;
    private String senha;

    private String email;

    private boolean ativo;
    private LocalDate dataCadastro;
    private LocalDate ultimoAcesso;

    private EPerfil perfil;

    public User(Long id, String login, String senha, String email, boolean ativo, LocalDate dataCadastro,
                LocalDate ultimoAcesso, EPerfil perfil) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.email = email;
        this.perfil = perfil;
        this.ativo = true;
        this.dataCadastro = LocalDate.now();
        this.ultimoAcesso = ultimoAcesso;
    }


    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public LocalDate getUltimoAcesso() {
        return ultimoAcesso;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public void setUltimoAcesso(LocalDate ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public EPerfil getPerfil() {
        return perfil;
    }

    public void setPerfil(EPerfil perfil) {
        this.perfil = perfil;
    }
}
