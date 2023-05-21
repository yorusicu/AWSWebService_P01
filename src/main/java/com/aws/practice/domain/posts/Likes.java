package com.aws.practice.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@IdClass(LikesPK.class)
public class Likes extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @Id
    private Long postId;

    @Id
    private Long replId;

    private String author;

    @Builder
    public Likes(String author) {
        this.author = author;
    }
}
