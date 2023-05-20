package com.aws.practice.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepo;

    @AfterEach
    public void cleanup() {
        postsRepo.deleteAll();
    }

    @Test
    void testBringWrite() {
        // given
        String title = "Test Title";
        String content = "Test Content";

        postsRepo.save(Posts.builder()
                .title(title)
                .content(content)
                .author("writer")
                .build());

        // when
        List<Posts> postsList = postsRepo.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}