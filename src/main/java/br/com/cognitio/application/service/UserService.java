package br.com.cognitio.application.service;

import br.com.cognitio.application.exception.EmailAlreadyExistsException;
import br.com.cognitio.application.exception.LoginAlreadyExistsException;
import br.com.cognitio.domain.model.User;
import br.com.cognitio.domain.port.in.UserUseCase;
import br.com.cognitio.domain.port.out.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements UserUseCase {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User createUser(User user) {
        logger.info("Tentando criar usuário com email: {}", user.getEmail());
        try {
           validateUserUnique(user);
            User savedUser = userRepository.save(user);
            logger.info("Usuário criado com sucesso com ID: {}", savedUser.getId());
            return savedUser;
        } catch (IllegalStateException e) {
            logger.error("Erro ao criar usuário: {}", e.getMessage());
            throw e;
        }

    }

    private void validateUserUnique(User user) {
        userRepository.findByEmail(user.getEmail())
                .ifPresent(u -> {
                    throw new EmailAlreadyExistsException("E-mail já está em uso");
                });
        userRepository.findByLogin(user.getLogin())
                .ifPresent(u -> {
                    throw new LoginAlreadyExistsException("Login já está em uso");
                });
    }

    @Transactional
    @Override
    public User updateUser(Long userId, User userToUpdate) {
        try {
            User existingUser = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado para o ID: " + userId));

            existingUser.setEmail(userToUpdate.getEmail());
            existingUser.setLogin(userToUpdate.getLogin());
            existingUser.setSenha(userToUpdate.getSenha());

            return userRepository.save(existingUser);
        } catch (IllegalStateException e) {
            logger.error("Erro ao editar usuário: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public List<User> findAllUsers() {
        try {
            return userRepository.findAllUsers();
        } catch (EntityNotFoundException e) {
            throw new RuntimeException("Nenhum usuário encontrado");
        }
    }

    @Override
    public User findUserById(Long userId) {
        try {
            return userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID:" + userId));
        } catch (IllegalStateException e) {
            logger.error("Erro ao procurar usuário: {}", e.getMessage());
            throw e;
        }
    }


}
