package br.com.cognitio.infrastructure.persistence.repository;

import br.com.cognitio.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByLogin(String login);

    Optional<UserEntity> findById(Long id);
}
