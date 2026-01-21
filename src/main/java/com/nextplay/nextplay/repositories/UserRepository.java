package com.nextplay.nextplay.repositories;

import com.nextplay.nextplay.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository <UserEntity, Long> {

    boolean existsByEmail(String email);

    Optional<UserEntity> findByEmail(String email);
}
