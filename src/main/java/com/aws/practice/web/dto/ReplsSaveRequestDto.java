package com.aws.practice.web.dto;

import com.aws.practice.domain.posts.Posts;
import com.aws.practice.domain.posts.Repls;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor
public class ReplsSaveRequestDto {
    private String content;
    private String author;
    private Long postId;
    private Long replId;
    private String delYn;

    @Builder
    public ReplsSaveRequestDto(String content, String author, Long postId, Long replId, String delYn) {
        this.content = content;
        this.author = author;
        this.postId = postId;
        this.replId = replId;
        this.delYn = delYn;
    }

    public Repls toEntity(Posts posts, String delYn) {
        return Repls.builder()
                .content(content)
                .author(author)
                .posts(posts)
                .delYn(delYn)
                .build();
    }
}
