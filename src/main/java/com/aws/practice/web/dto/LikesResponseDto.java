package com.aws.practice.web.dto;

import com.aws.practice.domain.posts.Likes;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Setter
@Getter
public class LikesResponseDto {
    private Long likeId;
    private Long replId;
    private Long postId;
    private String content;
    private String author;
    private List<ReplsResponseDto> replResList;

    public LikesResponseDto(Likes entity) {
        this.likeId = entity.getLikeId();
        this.replId = entity.getReplId();
        this.postId = entity.getPostId();
        this.author = entity.getAuthor();
    }
}
