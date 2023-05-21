package com.aws.practice.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "POSTS")
public class Posts extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long postId;

    @Column(name = "TITLE", length = 50, nullable = false)
    private String title;

    @Column(name = "CONTENT", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "AUTHOR")
    private String author;

    @OneToMany(mappedBy = "posts", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Repls> repls;

    @Builder
    public Posts(String title, String content, String author, List<Repls> repls) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.repls = repls;
    }

    public void updatePost(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
