package com.nextplay.nextplay.repositories;

import com.nextplay.nextplay.entities.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfileEntity, Long> {

    boolean existsByDisplayName(String displayName);
}
