package com.aws.practice.web;

import com.aws.practice.service.posts.PostsService;
import com.aws.practice.web.dto.PostsResponseDto;
import com.aws.practice.web.dto.PostsSaveRequestDto;
import com.aws.practice.web.dto.PostsUpdateRequestDto;
import com.aws.practice.web.dto.ReplsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class PostsApiController {
    private final PostsService postsServ;

    /**
     * Post등록
     * @param reqDto request
     * */
    @PostMapping("/c-posts")
    public Long createPost(@RequestBody PostsSaveRequestDto reqDto){
        return postsServ.createPost(reqDto);
    }

    /**
     * Repl등록
     * @param reqDto request
     * */
    @PostMapping("/c-repls/{postId}")
    public Long createRepl(@PathVariable Long postId, @RequestBody ReplsSaveRequestDto reqDto){
        return postsServ.createRepl(postId, reqDto);
    }

    /**
     * Post수정
     * @param postId postId
     * @param reqDto request
     * */
    @PutMapping("/u-posts/{postId}")
    public Long updatePosts(@PathVariable Long postId, @RequestBody PostsUpdateRequestDto reqDto){
        return postsServ.updatePost(postId, reqDto);
    }

    /**
     * Post삭제
     * @param postId postId
     * @param reqDto request
     * */
    @PutMapping("/d-posts/{postId}")
    public Long deletePosts(@PathVariable Long postId, @RequestBody PostsUpdateRequestDto reqDto){
        return postsServ.deletePost(postId, reqDto);
    }

    /**
     * Post조회(단건)
     * @param postId postId
     * */
    @GetMapping("/r-posts/{postId}")
    public PostsResponseDto findById(@PathVariable Long postId){
        return postsServ.findByPostId(postId);
    }

    /**
     * Post조회
     * @return List<PostsResponseDto>
     * */
    @GetMapping("/r-posts")
    public List<PostsResponseDto> findAllPost(){

        return postsServ.findAllPost();
    }


}
