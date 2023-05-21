package com.aws.practice.domain.posts;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class LikesPK implements Serializable {
    private Long likeId;

    private Long postId;

    private Long replId;
}
