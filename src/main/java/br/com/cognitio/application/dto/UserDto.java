package br.com.cognitio.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;
@NoArgsConstructor
public class UserDto {

    @NotBlank
    private String login;

    private String email;
    private String senha;

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
