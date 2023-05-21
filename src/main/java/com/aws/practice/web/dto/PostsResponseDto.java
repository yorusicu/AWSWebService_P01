package com.aws.practice.web.dto;

import com.aws.practice.domain.posts.Posts;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class PostsResponseDto {
    private Long postId;
    private String title;
    private String content;
    private String author;
    private Long ReplCnt;
    private Long LikeCnt;

    public PostsResponseDto(Posts entity) {
        this.postId = entity.getPostId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
