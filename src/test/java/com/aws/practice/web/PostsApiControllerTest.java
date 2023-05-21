package com.aws.practice.web;

import com.aws.practice.domain.posts.Posts;
import com.aws.practice.domain.posts.PostsRepository;
import com.aws.practice.web.dto.PostsSaveRequestDto;
import com.aws.practice.web.dto.PostsUpdateRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepo;

    @AfterEach
    public void tearDown() throws Exception {
        postsRepo.deleteAll();
    }

    @Test
    void regstPosts() throws Exception {
        // given
        String title = "title";
        String content = "content";

        PostsSaveRequestDto reqDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url = "http://127.0.0.1:" + port + "/api/v1/posts";
        // when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, reqDto, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepo.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    void updatePosts() throws Exception {
        // given
        Posts updatePosts = postsRepo.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        Long updateId = updatePosts.getPostId();
        String expectedTitle = "expectedTitle";
        String expectedContent = "expectedContent";

        PostsUpdateRequestDto reqDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://127.0.0.1:" + port + "/api/v1/posts/"+updateId;

        HttpEntity<PostsUpdateRequestDto> reqEntity = new HttpEntity<>(reqDto);

        // when
        ResponseEntity<Long> resEntity = restTemplate.exchange(url, HttpMethod.PUT, reqEntity, Long.class);

        // then
        assertThat(resEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(resEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepo.findAll();

        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);

    }
}