package br.com.cognitio.application.mapper;

import br.com.cognitio.application.dto.UserDto;
import br.com.cognitio.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto(User user);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCadastro", ignore = true)
    @Mapping(target = "ativo", ignore = true)
    @Mapping(target = "ultimoAcesso", ignore = true)
    User userDtoToUser(UserDto userDto);


}
