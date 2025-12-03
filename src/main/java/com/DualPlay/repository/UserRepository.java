package com.DualPlay.repository;

import com.DualPlay.entities.User;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    // Para autenticación con JWT
    Optional<User> findByEmail(String email);


}
