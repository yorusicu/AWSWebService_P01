package com.aws.practice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
@Table(name = "comments")
public class Comments extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @Column(name = "comment_content", length = 500, nullable = false)
    private String content;

    @Column(name = "del_yn", length = 2)
//    @ColumnDefault("N")
    private String delYn;

//    private Posts posts;

//    private Members member;
}
