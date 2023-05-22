package com.aws.practice.service.posts;

import com.aws.practice.domain.posts.Posts;
import com.aws.practice.domain.posts.PostsRepository;
import com.aws.practice.domain.posts.Repls;
import com.aws.practice.domain.posts.ReplsRepository;
import com.aws.practice.web.dto.PostsResponseDto;
import com.aws.practice.web.dto.ReplsResponseDto;
import com.aws.practice.web.dto.ReplsSaveRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
class PostsServiceTest {
    @Autowired
    PostsRepository postsRepo;

    @Autowired
    ReplsRepository replsRepo;

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
//        for (int i = 1; i < 11; i++) {
//            postsRepo.save(Posts.builder()
//                    .title(title + "_" + i)
//                    .content(content + "_" + i)
//                    .author(author + "_" + i)
//                    .build());
//        }
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
            dto.setReplCnt((long) (Math.random() * 100));
            dto.setLikeCnt((long) (Math.random() * 100));

            list.add(dto);
        }
        // then
        log.info(">>>> list: {}", list);
    }

    @Transactional
    @Test
    void bringList() {
        List<Posts> pr = postsRepo.findAll();
        List<PostsResponseDto> list = new ArrayList<>();

        for (Posts entity : pr) {
            PostsResponseDto dto = new PostsResponseDto(entity);
            dto.setPostId(entity.getPostId());
            dto.setTitle(entity.getTitle());
            dto.setContent(entity.getContent());
            dto.setAuthor(entity.getAuthor());
            dto.setReplCnt((long) (Math.random() * 100));
            dto.setLikeCnt((long) (Math.random() * 100));
            List<Repls> reDto = entity.getRepls();

            List<ReplsResponseDto> rlist = new ArrayList<>();
            for (Repls repl : reDto) {
                ReplsResponseDto replDto = new ReplsResponseDto(repl);
                replDto.setReplId(repl.getReplId());
                replDto.setPostId(repl.getPosts().getPostId());
                replDto.setContent(repl.getContent());
                replDto.setAuthor(repl.getAuthor());

                rlist.add(replDto);
            }

            dto.setReplsList(rlist);
            list.add(dto);
        }
        log.info("list:{}", list);
    }

    @Transactional
    @Test
    void deletePost() {
        // given
        Long postId = 5L;

        // when
        // DB에 id를 조회해 없으면 에러메세지를 띄움
        Posts posts = postsRepo.findById(postId).orElseThrow(() -> new IllegalArgumentException(("error" + postId)));

        // 게시글의 댓글있는지 조회
        List<Repls> repls = replsRepo.findByPostId(postId);
        List<ReplsSaveRequestDto> replsSaveRequestDtos = new ArrayList<>();
        repls.stream().forEach(repls1 -> {
//            log.info("repls1 : {}", repls1);
            ReplsSaveRequestDto dto = new ReplsSaveRequestDto().builder()
                    .postId(repls1.getPostId())
                    .replId(repls1.getReplId())
                    .content(repls1.getContent())
                    .author(repls1.getAuthor())
                    .delYn("Y")
                    .build();
            replsSaveRequestDtos.add(dto);
        });
        log.info("replsSaveRequestDtos : {}",replsSaveRequestDtos);
        // 게시글의 좋아요가 있는지 조회

        // 댓글이 있을 경우 댓글에 삭제여부를 Y로 정하고 수정

        // then
        // 좋아요가 있을 경우 삭제




        // 게시글의 삭제여부를 Y로 정하고 삭제

//        posts.updatePost(reqDto.getTitle(), reqDto.getContent());
    }
}