package br.com.cognitio.infrastructure.persistence.mapper;

import br.com.cognitio.domain.model.User;
import br.com.cognitio.infrastructure.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserEntityMapper {
    UserEntityMapper INSTANCE = Mappers.getMapper(UserEntityMapper.class);
    UserEntity userToUserEntity (User user);
    User userEntityToUser(UserEntity userEntity);
}
