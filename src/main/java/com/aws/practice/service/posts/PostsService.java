package com.aws.practice.service.posts;

import com.aws.practice.domain.posts.Posts;
import com.aws.practice.domain.posts.PostsRepository;
import com.aws.practice.web.dto.PostsResponseDto;
import com.aws.practice.web.dto.PostsSaveRequestDto;
import com.aws.practice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepo;

    String errMsg = "해당 게시글이 없습니다.";

    /**
     * Post등록
     * @param reqDto request
    * */
    @Transactional
    public Long save(PostsSaveRequestDto reqDto) {
        return postsRepo.save(reqDto.toEntity()).getId();
    }

    /**
     * Post수정
     * @param id ID
     * @param reqDto request
     * @return id
     * */
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto reqDto) {
        // DB에 id를 조회해 없으면 에러메세지를 띄움
        Posts posts = postsRepo.findById(id).orElseThrow(() -> new IllegalArgumentException((errMsg + id)));

        posts.update(reqDto.getTitle(), reqDto.getContent());

        return id;
    }

    /**
     * Post조회
     * @param id ID
     * @return postsResponseDto
     * */
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepo.findById(id).orElseThrow(() -> new IllegalArgumentException(errMsg + id));

        return new PostsResponseDto(entity);
    }
}
