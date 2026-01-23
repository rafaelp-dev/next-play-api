package com.nextplay.nextplay.repositories;

import com.nextplay.nextplay.entities.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository <GameEntity, Long> {
}
