package br.com.cognitio.application.dto;

import br.com.cognitio.domain.model.Enum.EPerfil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PerfilDto {

    @NotBlank(message = "O campo LOGIN é obrigatório")
    private String login;

    @NotBlank(message = "O campo E-MAIL é obrigatório")
    private String email;

    @NotNull(message = "O campo PERFIL é obrigatório")
    private EPerfil perfil;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EPerfil getPerfil() {
        return perfil;
    }

    public void setPerfil(EPerfil perfil) {
        this.perfil = perfil;
    }
}
