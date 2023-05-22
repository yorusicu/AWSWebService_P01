package com.aws.practice.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
//@IdClass(ReplsPK.class)
@Table(name = "REPLS")
public class Repls extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REPL_ID")
    private Long replId;

    @Column(name = "POST_ID", nullable = false)
    private Long postId;

    @Column(name = "CONTENT", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "DEL_YN")
    private String delYn;

    @MapsId("POST_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID", referencedColumnName = "POST_ID", nullable = false)
    private Posts posts;

    @Builder
    public Repls(String content, String author, Posts posts, String delYn) {
        this.content = content;
        this.author = author;
        this.posts = posts;
        this.delYn = delYn;
        this.postId = posts.getPostId();
    }
}
