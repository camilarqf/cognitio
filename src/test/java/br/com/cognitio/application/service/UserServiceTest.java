package br.com.cognitio.application.service;

import br.com.cognitio.application.exception.EmailAlreadyExistsException;
import br.com.cognitio.domain.model.User;
import br.com.cognitio.domain.port.out.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    Long id = 1L;
    String email = "email@teste.com";
    String login = "login";
    String senha = "12345";
    boolean ativo = true;
    LocalDate dataCadastro = LocalDate.now();
    LocalDate ultimoAcesso = null;

    private User newUser;

    @BeforeEach
    public void setup() {

        newUser = new User(id, login, senha, email, ativo, dataCadastro, ultimoAcesso);
    }

    @Test
    void whenCreateUserWithNewEmail_thenUserIsSaved() {


        newUser = new User(id, login, senha,email,ativo, dataCadastro, ultimoAcesso);

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(newUser);

        User savedUser = userService.createUser(newUser);

        assertNotNull(savedUser);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void whenCreateUserWithEmailAlreadyExists_thenThrowException() {
         newUser = new User(id, login, senha,email,ativo, dataCadastro, ultimoAcesso);

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(newUser));

        assertThrows(EmailAlreadyExistsException.class, () -> {
            userService.createUser(newUser);
        });
    }
}
