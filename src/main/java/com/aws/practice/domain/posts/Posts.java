package com.aws.practice.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "")
    private Long id;

    @Column(
//            name = "",
            length = 50,
            nullable = false)
    private String title;

    @Column(
//            name = "",
            columnDefinition = "TEXT",
            nullable = false)
    private String content;

//    @Column(name = "")
    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
