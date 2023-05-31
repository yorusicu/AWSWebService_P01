package com.aws.practice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
@Table(name = "posts")
public class Posts extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;
    @Column(name = "post_subject", length = 50)
    private String subject;
    @Lob @Column(name = "post_content")
    private String content;
    @Column(name = "del_yn", length = 2)
//    @ColumnDefault("N")
    private String delYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Members members;

    @Builder
    public Posts(String title, String content, String delYn) {
        this.subject = title;
        this.content = content;
        this.delYn = delYn;
    }
}
