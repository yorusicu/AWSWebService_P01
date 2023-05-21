package com.aws.practice.service.posts;

import com.aws.practice.domain.posts.Posts;
import com.aws.practice.domain.posts.PostsRepository;
import com.aws.practice.domain.posts.Repls;
import com.aws.practice.domain.posts.ReplsRepository;
import com.aws.practice.web.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepo;
    private final ReplsRepository replsRepo;

    String errMsg = "해당 게시글이 없습니다.";

    /**
     * Post등록
     * @param reqDto request
    * */
    @Transactional
    public Long createPost(PostsSaveRequestDto reqDto) {
        return postsRepo.save(reqDto.toEntity()).getPostId();
    }

    /**
     * Post수정
     * @param postId postId
     * @param reqDto request
     * @return id
     * */
    @Transactional
    public Long update(Long postId, PostsUpdateRequestDto reqDto) {
        // DB에 id를 조회해 없으면 에러메세지를 띄움
        Posts posts = postsRepo.findById(postId).orElseThrow(() -> new IllegalArgumentException((errMsg + postId)));

        posts.updatePost(reqDto.getTitle(), reqDto.getContent());

        return postId;
    }

    /**
     * Post조회(단건)
     * @param postId postId
     * @return postsResponseDto
     * */
    public PostsResponseDto findByPostId(Long postId) {
        Posts entity = postsRepo.findById(postId).orElseThrow(() -> new IllegalArgumentException(errMsg + postId));

        return new PostsResponseDto(entity);
    }

    /**
     * Post조회
     * @return List<PostsResponseDto>
     * */
    public List<PostsResponseDto> findAllPost() {
        // 1:N으로 전체조회
        List<Posts> postsList = postsRepo.findAll();
        // list 생성 및 초기화
        List<PostsResponseDto> list = new ArrayList<>();

        // PUT OUTDATA
        for (Posts entity : postsList) {
            // PostsResponse객체에 Entity 담기
            PostsResponseDto dto = new PostsResponseDto(entity);
            dto.setPostId(entity.getPostId());
            dto.setTitle(entity.getTitle());
            dto.setContent(entity.getContent());
            dto.setAuthor(entity.getAuthor());
            dto.setReplCnt((long) (Math.random() * 100));
            dto.setLikeCnt((long) (Math.random() * 100));

            // posts에 조회 된 replsEntity를 repls 리스트에 넣기
            List<Repls> reDto = entity.getRepls();
            // ReplsResponse객체에 Entity 담기
            List<ReplsResponseDto> rlist = new ArrayList<>();
            for (Repls repl : reDto) {
                ReplsResponseDto replDto = new ReplsResponseDto(repl);
                replDto.setReplId(repl.getReplId());
                replDto.setPostId(repl.getPosts().getPostId());
                replDto.setContent(repl.getContent());
                replDto.setAuthor(repl.getAuthor());

                rlist.add(replDto);
            }
            // entity > response 리스트로 변환
            list.add(dto);
            dto.setReplsList(rlist);
        }
        log.info("list:{}", list);

        return list;
    }

    /**
     * Repl등록
     * @param reqDto request
     * */
    @Transactional
    public Long createRepl(Long postId, ReplsSaveRequestDto reqDto) {
        log.info("postId: {}", postId);
        log.info("ReqDto: {}", reqDto);
        // Posts 조회
        Posts posts = postsRepo.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException((errMsg + postId)));

        Repls repls = reqDto.toEntity(posts);
        log.info("repls.getPostId(): {}", repls.getPosts().getPostId());

        Repls saveRepl = replsRepo.save(repls);

        return saveRepl.getReplId();
    }
}
