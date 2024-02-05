package br.com.cognitio.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "TB_USUARIO")
public class UserEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -6094014741389292790L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private String senha;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyyy")
    private LocalDate dataCadastro;
    @Column(nullable = false)
    private boolean ativo;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate ultimoAcesso;

}
