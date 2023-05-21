package com.aws.practice.domain.posts;

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

    @Column(name = "CONTENT", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "AUTHOR")
    private String author;

    @MapsId("POST_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID", referencedColumnName = "POST_ID", nullable = false)
    private Posts posts;
}
