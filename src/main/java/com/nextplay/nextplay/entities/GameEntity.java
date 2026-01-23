package com.nextplay.nextplay.entities;

import com.nextplay.nextplay.enums.GameStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "games")
@Getter
@Setter
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "profile_id", nullable = false)
    private UserProfileEntity profile;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private GameStatus status;

    @Column(nullable = false)
    private Boolean favorite;

    @Column(nullable = false)
    private LocalDateTime addedAt;

    @Column(nullable = false)
    private Long rating;

    @Column(nullable = true)
    private String review;

    public GameEntity () {}

    public GameEntity(UserProfileEntity profile, String title, GameStatus status, Boolean favorite, Long rating, String review) {
        this.profile = profile;
        this.title = title;
        this.status = status;
        this.favorite = favorite;
        this.addedAt = LocalDateTime.now();
        this.rating = rating;
        this.review = review;
    }
}
