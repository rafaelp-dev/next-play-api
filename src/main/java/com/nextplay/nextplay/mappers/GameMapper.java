package com.nextplay.nextplay.mappers;

import com.nextplay.nextplay.dtos.request.GameRequest;
import com.nextplay.nextplay.entities.GameEntity;
import com.nextplay.nextplay.entities.UserProfileEntity;
import org.springframework.stereotype.Component;

@Component
public class GameMapper {

    public GameEntity toGame (UserProfileEntity userProfileEntity, GameRequest gameRequest) {
        return new GameEntity(
                userProfileEntity,
                gameRequest.title(),
                gameRequest.status(),
                gameRequest.favorite(),
                gameRequest.rating(),
                gameRequest.review()
        );
    }
}
