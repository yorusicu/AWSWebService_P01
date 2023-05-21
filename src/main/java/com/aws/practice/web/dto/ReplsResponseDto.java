package com.aws.practice.web.dto;

import com.aws.practice.domain.posts.Repls;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ReplsResponseDto {
    private Long replId;
    private Long postId;
    private String content;
    private String author;

    public ReplsResponseDto(Repls entity) {
        this.replId = entity.getReplId();
//        this.postId = entity.getPostId();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
