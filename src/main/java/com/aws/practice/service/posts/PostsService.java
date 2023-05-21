package com.aws.practice.service.posts;

import com.aws.practice.domain.posts.Posts;
import com.aws.practice.domain.posts.PostsRepository;
import com.aws.practice.web.dto.PostsResponseDto;
import com.aws.practice.web.dto.PostsSaveRequestDto;
import com.aws.practice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

        posts.update(reqDto.getTitle(), reqDto.getContent());

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
        List<Posts> postsList = postsRepo.findAll();

        List<PostsResponseDto> list = new ArrayList<>();

        for (Posts entity:
             postsList) {
            PostsResponseDto dto = new PostsResponseDto(entity);
            BeanUtils.copyProperties(entity, dto);
            if (dto.getReplCnt() == null) { dto.setReplCnt(0L);}
            if (dto.getLikeCnt() == null) { dto.setLikeCnt(0L);}

            list.add(dto);
        }

        return list;
    }
}
