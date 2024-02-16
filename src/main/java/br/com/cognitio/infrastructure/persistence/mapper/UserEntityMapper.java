package br.com.cognitio.infrastructure.persistence.mapper;

import br.com.cognitio.domain.model.Enum.EPerfil;
import br.com.cognitio.domain.model.User;
import br.com.cognitio.infrastructure.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserEntityMapper {
    UserEntityMapper INSTANCE = Mappers.getMapper(UserEntityMapper.class);
    @Mapping(target = "perfil", source = "perfil")
    UserEntity userToUserEntity (User user);
    @Mapping(target = "perfil", source = "perfil")
    User userEntityToUser(UserEntity userEntity);

    default EPerfil mapPerfilFromCodigo(Integer codigo) {
        for (EPerfil perfil : EPerfil.values()) {
            if (perfil.getCodigo().equals(codigo)) {
                return perfil;
            }
        }
        return null;
    }

    default Integer mapPerfilToCodigo(EPerfil perfil) {
        return perfil != null ? perfil.getCodigo() : null;
    }



}
