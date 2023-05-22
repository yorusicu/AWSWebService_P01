package com.aws.practice.web.dto;

import com.aws.practice.domain.posts.Posts;
import com.aws.practice.domain.posts.Repls;
import lombok.*;

@ToString
@Setter
@Getter
@NoArgsConstructor
public class ReplsSaveRequestDto {
    private String content;
    private String author;
    private Long postId;

    @Builder
    public ReplsSaveRequestDto(String content, String author, Long postId) {
        this.content = content;
        this.author = author;
        this.postId = postId;
    }

    public Repls toEntity(Posts posts) {
        return Repls.builder()
                .content(content)
                .author(author)
                .posts(posts)
                .build();
    }
}
