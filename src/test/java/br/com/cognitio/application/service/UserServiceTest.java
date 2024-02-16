package br.com.cognitio.application.service;

import br.com.cognitio.application.exception.EmailAlreadyExistsException;
import br.com.cognitio.domain.model.Enum.EPerfil;
import br.com.cognitio.domain.model.User;
import br.com.cognitio.domain.port.out.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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

    private EPerfil perfil;

    private User newUser;
    private User existingUser;
    private User updatedUser;

    @BeforeEach
    public void setup() {

        newUser = new User(id, login, senha, email, ativo, dataCadastro, ultimoAcesso, perfil);
        existingUser = new User(1L, "existingLogin", "existingPassword", "existingEmail@example.com", true, null, null, EPerfil.ALUNO);
        updatedUser = new User(1L, "updatedLogin", "updatedPassword", "updatedEmail@example.com", true, null, null, EPerfil.ALUNO);
        when(userRepository.findById(existingUser.getId())).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);
    }

    @Test
    void whenCreateUserWithNewEmail_thenSuccess() {


        newUser = new User(id, login, senha, email, ativo, dataCadastro, ultimoAcesso, perfil);

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(newUser);

        User savedUser = userService.createUser(newUser);

        assertNotNull(savedUser);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void whenCreateUserWithEmailAlreadyExists_thenThrowException() {
         newUser = new User(id, login, senha, email, ativo, dataCadastro, ultimoAcesso, perfil);

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

    @Test
    void whenGelAllUsers_thenSuccess() {

        List<User> expectedUsers = Arrays.asList(newUser, existingUser);
        when(userRepository.findAllUsers()).thenReturn(expectedUsers);

        List<User> result = userService.findAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expectedUsers, result);
        verify(userRepository).findAllUsers();
    }

    @Test
    void getAllUsers_NoUsersFound() {

        when(userRepository.findAllUsers()).thenReturn(Collections.emptyList());

        List<User> result = userService.findAllUsers();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(userRepository).findAllUsers();
    }

    @Test
    void getUserById_Success() {

        when(userRepository.findById(id)).thenReturn(Optional.of(existingUser));


        User result = userService.findUserById(id);

        assertNotNull(result);
        assertEquals(existingUser, result);
        verify(userRepository).findById(id);
    }

    @Test
    void getUserById_NotFound() {

        Long invalidId = 99L;
        when(userRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.findUserById(invalidId));
        verify(userRepository).findById(invalidId);
    }

    @Test
    void whenBlockUser_thenSuccess() {

        when(userRepository.findById(id)).thenReturn(Optional.of(newUser));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User blockedUser = userService.blockUser(id);

        assertNotNull(blockedUser);
        assertFalse(blockedUser.getAtivo());
        verify(userRepository, times(1)).findById(id);
        verify(userRepository, times(1)).save(newUser);
    }

    @Test
    void whenUnBlockUser_thenSuccess() {


        when(userRepository.findById(id)).thenReturn(Optional.of(newUser));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        User unblockedUser = userService.unBlockUser(id);

        assertNotNull(unblockedUser);
        assertTrue(unblockedUser.getAtivo());
        verify(userRepository, times(1)).findById(id);
        verify(userRepository, times(1)).save(newUser);
    }

    @Test
    void whenBlockUserWithNonExistingId_thenThrowEntityNotFoundException() {
        Long nonExistingUserId = 99L;

        when(userRepository.findById(nonExistingUserId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.blockUser(nonExistingUserId));
        verify(userRepository, times(1)).findById(nonExistingUserId);
    }

    @Test
    void whenUnBlockUserWithNonExistingId_thenThrowEntityNotFoundException() {
        Long nonExistingUserId = 99L;

        when(userRepository.findById(nonExistingUserId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.unBlockUser(nonExistingUserId));
        verify(userRepository, times(1)).findById(nonExistingUserId);
    }

    @Test
    void whenUpdatePerfilUser_thenSuccess() {

        User updatedInfo = new User(id, login, senha, "newEmail@example.com", ativo, dataCadastro, ultimoAcesso, EPerfil.ADMIN);

        when(userRepository.findById(id)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));


        User result = userService.updatePerfilUser(id, updatedInfo);

        assertNotNull(result);
        assertEquals(EPerfil.ADMIN, result.getPerfil());
        assertEquals("newEmail@example.com", result.getEmail());
        assertEquals(login, result.getLogin());
        verify(userRepository).findById(id);
        verify(userRepository).save(existingUser);
    }

    @Test
    void whenUpdatePerfilUserWithInvalidId_thenThrowEntityNotFoundException() {

        Long invalidUserId = 99L;
        User updatedInfo = new User(invalidUserId, login, senha, email, ativo, dataCadastro, ultimoAcesso, EPerfil.ADMIN);

        when(userRepository.findById(invalidUserId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.updatePerfilUser(invalidUserId, updatedInfo));
        verify(userRepository).findById(invalidUserId);
        verify(userRepository, never()).save(any(User.class));
    }






}
