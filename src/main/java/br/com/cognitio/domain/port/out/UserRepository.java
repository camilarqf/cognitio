package br.com.cognitio.domain.port.out;

import br.com.cognitio.domain.dto.UserDto;
import br.com.cognitio.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User>findByEmail(String email);
}
