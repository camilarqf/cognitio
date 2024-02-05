package br.com.cognitio.infrastructure.persistence.repository;

import br.com.cognitio.application.service.UserService;
import br.com.cognitio.domain.dto.UserDto;
import br.com.cognitio.domain.model.User;
import br.com.cognitio.domain.port.out.UserRepository;
import br.com.cognitio.infrastructure.persistence.entity.UserEntity;
import br.com.cognitio.infrastructure.persistence.mapper.UserEntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryImpl implements UserRepository {

    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);
    private final SpringDataUserRepository springDataUserRepository;

    public UserRepositoryImpl(SpringDataUserRepository springDataUserRepository) {
        this.springDataUserRepository = springDataUserRepository;
    }

    @Override
    public User save(User user) {
        logger.debug("Salvando usuário no banco de dados: {}", user);
        UserEntity userEntity = UserEntityMapper.INSTANCE.userToUserEntity(user);
        UserEntity saveUserEntity = springDataUserRepository.save(userEntity);
        return UserEntityMapper.INSTANCE.userEntityToUser(saveUserEntity);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<UserEntity> userEntity = springDataUserRepository.findByEmail(email);
        return userEntity.map(UserEntityMapper.INSTANCE::userEntityToUser);
    }
}
