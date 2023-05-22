package com.aws.practice.web.dto;

import com.aws.practice.domain.posts.Repls;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ReplsResponseDto implements Comparable<ReplsResponseDto> {
    private Long replId;
    private Long postId;
    private String content;
    private String author;

    public ReplsResponseDto(Repls entity) {
        this.replId = entity.getReplId();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }

    @Override
    public int compareTo(ReplsResponseDto replResDto) {
        return this.getReplId().compareTo(replResDto.getReplId()) * -1;
    }
}
