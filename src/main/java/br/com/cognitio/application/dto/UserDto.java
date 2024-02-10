package br.com.cognitio.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;
@NoArgsConstructor
public class UserDto {

    @NotBlank(message = "O campo LOGIN é obrigatório")
    private String login;

    @NotBlank(message = "O campo E-MAIL é obrigatório")
    private String email;

    @NotBlank(message = "O campo SENHA é obrigatório")
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
