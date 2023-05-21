package com.aws.practice.service.posts;

import com.aws.practice.domain.posts.Posts;
import com.aws.practice.domain.posts.PostsRepository;
import com.aws.practice.web.dto.PostsResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
class PostsServiceTest {
    @Autowired
    PostsRepository postsRepo;

//    @AfterEach
//    public void cleanup() {
//        postsRepo.deleteAll();
//    }

    String title = "Test Title";
    String content = "Test Content";
    String author = "writer";

    @Test
    void findAllPost() {
        // given
        for (int i = 1; i < 11; i++) {
            postsRepo.save(Posts.builder()
                    .title(title + "_" + i)
                    .content(content + "_" + i)
                    .author(author + "_" + i)
                    .build());
        }
        // repl, like ??



        // when
        List<Posts> postsList = postsRepo.findAll();
        log.info("postList: {}", postsList);
        List<PostsResponseDto> list = new ArrayList<>();

        for (Posts entity : postsList) {
            PostsResponseDto dto = new PostsResponseDto(entity);
            dto.setPostId(entity.getPostId());
            dto.setTitle(entity.getTitle());
            dto.setContent(entity.getContent());
            dto.setAuthor(entity.getAuthor());
            dto.setReplCnt((long)(Math.random()*100));
            dto.setLikeCnt((long)(Math.random()*100));

            list.add(dto);
        }
        // then
        log.info(">>>> list: {}", list);
    }
}