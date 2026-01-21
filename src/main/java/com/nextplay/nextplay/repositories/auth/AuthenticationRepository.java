package com.nextplay.nextplay.repositories.auth;

import com.nextplay.nextplay.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationRepository extends JpaRepository<UserEntity, Long> {

    UserDetails findByEmail(String email);

}
