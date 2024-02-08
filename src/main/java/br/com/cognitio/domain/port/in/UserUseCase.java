package br.com.cognitio.domain.port.in;

import br.com.cognitio.domain.model.User;

import java.util.List;

public interface UserUseCase {
    User createUser(User user);
    User updateUser(Long userId, User user);
    List<User> findAllUsers();
    User findUserById(Long userId);

}
