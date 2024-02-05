package br.com.cognitio.application.service;

import br.com.cognitio.domain.model.User;
import br.com.cognitio.domain.port.in.UserUseCase;
import br.com.cognitio.domain.port.out.UserRepository;

import br.com.cognitio.application.exception.EmailAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
            userRepository.findByEmail(user.getEmail())
                    .ifPresent(u -> {
                        throw new EmailAlreadyExistsException("E-mail já está em uso");
                    });
            User savedUser = userRepository.save(user);
            logger.info("Usuário criado com sucesso com ID: {}", savedUser.getId());
            return savedUser;
        } catch (EmailAlreadyExistsException e) {
            logger.error("Erro ao tentar criar usuário: E-mail já está em uso",  e.getMessage());
            throw e;
        } catch (IllegalStateException e) {
            logger.error("Erro ao criar usuário: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Erro inesperado ao criar usuário", e);
            throw new RuntimeException("Erro ao criar usuário", e);
        }

    }


}
