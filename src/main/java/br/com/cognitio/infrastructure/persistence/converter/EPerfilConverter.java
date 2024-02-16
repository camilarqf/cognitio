package br.com.cognitio.infrastructure.persistence.converter;

import br.com.cognitio.domain.model.Enum.EPerfil;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EPerfilConverter implements AttributeConverter<EPerfil, Integer> {
    @Override
    public Integer convertToDatabaseColumn(EPerfil ePerfil) {
        if(ePerfil == null){
            return null;
        }
        return ePerfil.getCodigo();
    }

    @Override
    public EPerfil convertToEntityAttribute(Integer codigo) {
        if(codigo == null){
            return null;
        }
        for(EPerfil ePerfil : EPerfil.values()){
            if(ePerfil.getCodigo().equals(codigo)){
                return ePerfil;
            }
        }
        throw new IllegalArgumentException("Código inválido: " + codigo);
    }
}
