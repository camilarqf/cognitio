package br.com.cognitio.domain.port.in;

import br.com.cognitio.domain.model.User;

import java.util.List;

public interface UserUseCase {
    User createUser(User user);
    User updateUser(Long userId, User user);
    List<User> findAllUsers();
    User findUserById(Long userId);

    User blockUser(Long id);

    User unBlockUser(Long id);

    User updatePerfilUser(Long userId, User user);
}
