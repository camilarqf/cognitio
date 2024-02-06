package br.com.cognitio.infrastructure.api;

import br.com.cognitio.application.service.UserService;
import br.com.cognitio.application.dto.UserDto;
import br.com.cognitio.domain.model.User;
import br.com.cognitio.infrastructure.api.controller.UserController;
import br.com.cognitio.infrastructure.exception.advice.GlobalExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest {
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    Long id = 1L;
    String email = "email@teste.com";
    String login = "login";
    String senha = "12345";
    boolean ativo = true;
    LocalDate dataCadastro = LocalDate.now();
    LocalDate ultimoAcesso = null;

    private User user;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
        user = new User(id, login, senha, email, ativo, dataCadastro, ultimoAcesso);
    }

    @Test
    public void whenPostRequestToUserAndValidUser_thenCorrectResponse() throws Exception {
        UserDto userDto = new UserDto();

        userDto.setLogin(login);
        userDto.setEmail(email);
        userDto.setSenha(senha);

        user.setEmail(userDto.getEmail());

        given(userService.createUser(any(User.class))).willReturn(user);

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(userDto)));
    }

    @Test
    public void whenPostRequestToUserAndServiceThrowsException_thenErrorResponse() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setLogin("loginValue");
        userDto.setEmail("email@example.com");
        userDto.setSenha("senhaValue");

        // Simula uma exceção ao tentar criar um usuário
        doThrow(new RuntimeException("Erro na solicitação")).when(userService).createUser(any(User.class));

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isBadRequest()) // ou o status que você espera que seja retornado
                .andExpect(jsonPath("$.error").value("Erro na solicitação")); // Ajuste conforme o retorno esperado do seu GlobalExceptionHandler
    }

}
