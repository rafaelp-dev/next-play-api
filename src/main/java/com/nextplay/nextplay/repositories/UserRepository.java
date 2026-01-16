package com.nextplay.nextplay.repositories;

import com.nextplay.nextplay.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository <UserEntity, Long> {

    boolean existsByEmail(String email);

    UserDetails findByEmail(String email);
}
