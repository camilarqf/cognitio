/*
package br.com.cognitio.infrastructure.api;

import br.com.cognitio.application.exception.EmailAlreadyExistsException;
import br.com.cognitio.application.mapper.UserMapper;
import br.com.cognitio.application.service.UserService;
import br.com.cognitio.application.dto.UserDto;
import br.com.cognitio.domain.model.User;
import br.com.cognitio.domain.port.in.UserUseCase;
import br.com.cognitio.infrastructure.api.controller.UserController;
import br.com.cognitio.infrastructure.exception.advice.GlobalExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest {
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private UserUseCase userUseCase;

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
                .setValidator(validator())
                .build();
        user = new User(id, login, senha, email, ativo, dataCadastro, ultimoAcesso);


    }

    private Validator validator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();
        return localValidatorFactoryBean;
    }



    @Test
    void whenSaveUserWithValidData_thenSuccess() throws Exception {
        UserDto newUserDto = new UserDto();
        newUserDto.setEmail("valid@example.com");
        newUserDto.setLogin("ValidLogin");
        newUserDto.setSenha("ValidPassword123");

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUserDto)))
                .andExpect(status().isCreated());
    }


    // Falha ao atualizar um usuário (exemplo genérico de falha, ajuste conforme necessário)
    @Test
    void whenUpdateUserFails_thenRespondWithError() throws Exception {
        Long userId = 1L;
        UserDto userDtoToUpdate = new UserDto(); // Dados inválidos para simular falha

        mockMvc.perform(put("/user/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDtoToUpdate)))
                .andExpect(status().isBadRequest()); // Ajuste o status esperado conforme sua lógica de validação

        // Similarmente, não é necessário verificar interações com `userUseCase` devido à falha esperada antes da lógica de negócios
    }

   */
/* @Test
    void whenSaveUser_ThrowsEmailAlreadyExistsException_IfEmailExists() throws Exception {
        // Simula o serviço lançando EmailAlreadyExistsException quando createUser é chamado
        given(userUseCase.createUser(any(User.class))).willThrow(new EmailAlreadyExistsException("E-mail já está em uso"));

        // Dados do usuário em formato JSON
        String userJson = "{\"email\":\"existing@example.com\",\"password\":\"password123\"}";

        // Executa a requisição POST para criar o usuário e verifica o status de resposta
        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isBadRequest()); // Verifica se o status de resposta é 409 Conflict
    }*//*


    */
/*@Test
    void whenGetAllUsers_thenSuccess() throws Exception {
        List<User> users = List.of(new User(1L, "login1", "password", "email1@example.com", true, LocalDate.now(), LocalDate.now()));
        given(userUseCase.findAllUsers()).willReturn(users);

        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        verify(userUseCase).findAllUsers();
    }*//*


    @Test
    void whenGetUserById_thenSuccess() throws Exception {
        Long userId = 1L;
        User user = new User(userId, "login", "password", "email@example.com", true, LocalDate.now(), LocalDate.now());
        UserDto userDto = UserMapper.INSTANCE.userToUserDto(user); // Asume that you have already implemented this mapping

        given(userService.findUserById(eq(userId))).willReturn(user);

        mockMvc.perform(get("/user/{id}", userId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(user.getEmail()))
                .andExpect(jsonPath("$.login").value(user.getLogin()));
    }

   */
/* @Test
    void whenGetUserByIdWithNonExistingId_thenRespondWithNotFound() throws Exception {
        Long nonExistingUserId = 99L;

        when(userUseCase.findUserById(nonExistingUserId)).thenThrow(new EntityNotFoundException("Usuário não encontrado"));

        mockMvc.perform(get("/user/{id}", nonExistingUserId))
                .andExpect(status().isBadRequest());

        verify(userUseCase).findUserById(nonExistingUserId);
    }*//*



}
*/
