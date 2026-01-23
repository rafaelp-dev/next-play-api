package com.nextplay.nextplay.repositories;

import com.nextplay.nextplay.entities.UserEntity;
import com.nextplay.nextplay.entities.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfileEntity, Long> {

    boolean existsByDisplayName(String displayName);

    Optional<UserProfileEntity> findByUserUserId(Long userId);
}
