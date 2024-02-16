package br.com.cognitio.application.mapper;

import br.com.cognitio.application.dto.PerfilDto;
import br.com.cognitio.application.dto.UserDto;
import br.com.cognitio.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper
public interface PerfilMapper {

    PerfilMapper INSTANCE = Mappers.getMapper(PerfilMapper.class);

    PerfilDto userToPerfilDto(User user);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCadastro", ignore = true)
    @Mapping(target = "ativo", ignore = true)
    @Mapping(target = "ultimoAcesso", ignore = true)
    @Mapping(target = "senha", ignore = true)
    @Mapping(target = "perfil", source = "perfil")
    User perfilDtoToUser(PerfilDto userDto);
}
