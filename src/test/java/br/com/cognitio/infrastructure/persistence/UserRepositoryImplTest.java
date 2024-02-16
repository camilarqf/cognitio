package br.com.cognitio.infrastructure.persistence;

import br.com.cognitio.domain.model.Enum.EPerfil;
import br.com.cognitio.domain.model.User;
import br.com.cognitio.infrastructure.persistence.entity.UserEntity;
import br.com.cognitio.infrastructure.persistence.mapper.UserEntityMapper;
import br.com.cognitio.infrastructure.persistence.repository.SpringDataUserRepository;
import br.com.cognitio.infrastructure.persistence.repository.UserRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRepositoryImplTest {

    @Mock
    private SpringDataUserRepository springDataUserRepository;

    @InjectMocks
    private UserRepositoryImpl userRepositoryImpl;

    private User user;
    private UserEntity userEntity;

    Long id = 1L;
    String email = "email@teste.com";
    String login = "login";
    String senha = "12345";
    boolean ativo = true;
    LocalDate dataCadastro = LocalDate.now();
    LocalDate ultimoAcesso = LocalDate.now(); // Ajuste conforme necessário

    private EPerfil perfil;

    @BeforeEach
    void setUp() {

        user = new User(id, login, senha, email, ativo, dataCadastro, ultimoAcesso, perfil);
        userEntity = UserEntityMapper.INSTANCE.userToUserEntity(user);
    }

    @Test
    void whenSaveUser_thenSuccess() {
        when(springDataUserRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        User savedUser = userRepositoryImpl.save(user);

        assertNotNull(savedUser);
        assertEquals(email, savedUser.getEmail()); // Verificar se o email do usuário salvo é o esperado
        verify(springDataUserRepository).save(any(UserEntity.class));
    }

    @Test
    void whenFindByEmail_thenUserFound() {
        when(springDataUserRepository.findByEmail(eq(email))).thenReturn(Optional.of(userEntity));

        Optional<User> foundUser = userRepositoryImpl.findByEmail(email);

        assertTrue(foundUser.isPresent());
        assertEquals(email, foundUser.get().getEmail());
    }

    @Test
    void whenSaveUser_thenThrowException() {
        when(springDataUserRepository.save(any(UserEntity.class))).thenThrow(new RuntimeException("Database error"));

        Exception exception = assertThrows(RuntimeException.class, () -> userRepositoryImpl.save(user));
        assertEquals("Database error", exception.getMessage());
    }

    @Test
    void whenFindByEmail_thenUserNotFound() {
        when(springDataUserRepository.findByEmail(eq("nonexistent@example.com"))).thenReturn(Optional.empty());

        Optional<User> foundUser = userRepositoryImpl.findByEmail("nonexistent@example.com");

        assertFalse(foundUser.isPresent());
    }

    @Test
    void whenFindByLogin_thenUserFound() {
        when(springDataUserRepository.findByLogin(eq(login))).thenReturn(Optional.of(userEntity));

        Optional<User> foundUser = userRepositoryImpl.findByLogin(login);

        assertTrue(foundUser.isPresent());
        assertEquals(login, foundUser.get().getLogin());
    }

    @Test
    void whenFindByLogin_thenUserNotFound() {
        when(springDataUserRepository.findByLogin(eq("unknown"))).thenReturn(Optional.empty());

        Optional<User> foundUser = userRepositoryImpl.findByLogin("unknown");

        assertFalse(foundUser.isPresent());
    }

    @Test
    void whenFindAllUsersAndNoUsers_thenEmptyList() {
        when(springDataUserRepository.findAll()).thenReturn(Collections.emptyList());

        List<User> users = userRepositoryImpl.findAllUsers();

        assertTrue(users.isEmpty());
    }


}
