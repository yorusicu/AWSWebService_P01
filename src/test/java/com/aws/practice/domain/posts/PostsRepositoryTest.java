package com.aws.practice.domain.posts;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepo;

    @AfterEach
    public void cleanup() {
        postsRepo.deleteAll();
    }

    String title = "Test Title";
    String content = "Test Content";
    String author = "writer";

    @Test
    void testBringWrite() {
        // given

        postsRepo.save(Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build());

        // when
        List<Posts> postsList = postsRepo.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    void rgstBaseEntity(){
        // given
        LocalDateTime now = LocalDateTime.now();

        postsRepo.save(Posts.builder()
                .title(title)
                .content(content)
                .author(author).build());

        // when
        List<Posts> postsList = postsRepo.findAll();

        // then
        Posts posts = postsList.get(0);

        log.info("createDate: {}, modifiedDate: {}", posts.getCreatedDate(), posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}