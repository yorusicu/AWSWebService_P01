package com.aws.practice.web;

import com.aws.practice.service.posts.PostsService;
import com.aws.practice.web.dto.PostsResponseDto;
import com.aws.practice.web.dto.PostsSaveRequestDto;
import com.aws.practice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class PostsApiController {
    private final PostsService postsServ;

    /**
     * Post등록
     * @param reqDto request
     * */
    @PostMapping("/posts")
    public Long save(@RequestBody PostsSaveRequestDto reqDto){
        return postsServ.save(reqDto);
    }

    /**
     * Post수정
     * @param id ID
     * @param reqDto request
     * */
    @PutMapping("/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto reqDto){
        return postsServ.update(id, reqDto);
    }

    /**
     * Post조회(단건)
     * @param id ID
     * */
    @GetMapping("/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsServ.findByPostId(id);
    }

    /**
     * PostId조회
     * @param id ID
     * */
    @GetMapping("/posts")
    public PostsResponseDto findAllPost(@PathVariable Long id){
        return null;
    }

}
