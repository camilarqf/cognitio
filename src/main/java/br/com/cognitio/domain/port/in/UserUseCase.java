package br.com.cognitio.domain.port.in;

import br.com.cognitio.domain.model.User;

public interface UserUseCase {
    User createUser(User user);
    User updateUser(Long userId, User user);
}
