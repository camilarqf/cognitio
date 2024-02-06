package br.com.cognitio.application.service;

import br.com.cognitio.application.exception.EmailAlreadyExistsException;
import br.com.cognitio.domain.model.User;
import br.com.cognitio.domain.port.out.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
    private User existingUser;
    private User updatedUser;

    @BeforeEach
    public void setup() {

        newUser = new User(id, login, senha, email, ativo, dataCadastro, ultimoAcesso);
        existingUser = new User(1L, "existingLogin", "existingPassword", "existingEmail@example.com", true, null, null);
        updatedUser = new User(1L, "updatedLogin", "updatedPassword", "updatedEmail@example.com", true, null, null);
        when(userRepository.findById(existingUser.getId())).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);
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

    @Test
    void whenUpdateUser_thenSuccess() {
        User result = userService.updateUser(existingUser.getId(), updatedUser);

        assertNotNull(result);
        assertEquals(updatedUser.getEmail(), result.getEmail());
        assertEquals(updatedUser.getLogin(), result.getLogin());
        assertEquals(updatedUser.getSenha(), result.getSenha());

        verify(userRepository).save(any(User.class));
    }

    @Test
    void whenUpdateUserWithInvalidId_thenThrowEntityNotFoundException() {
        Long invalidUserId = 99L;
        when(userRepository.findById(invalidUserId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.updateUser(invalidUserId, updatedUser));

        verify(userRepository, never()).save(any(User.class));
    }
}
