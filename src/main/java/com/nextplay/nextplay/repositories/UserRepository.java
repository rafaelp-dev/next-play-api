package com.nextplay.nextplay.repositories;

import com.nextplay.nextplay.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <UserEntity, Long> {

    boolean existsByEmail(String email);

}
