package com.nextplay.nextplay.repositories;

import com.nextplay.nextplay.entities.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository <GameEntity, Long> {

    List<GameEntity> findByProfileProfileId(Long profileId);

    List<GameEntity> findByFavoriteTrue();

}
