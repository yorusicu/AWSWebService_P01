package com.aws.practice.service.posts;

import com.aws.practice.domain.posts.PostsRepository;
import com.aws.practice.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepo;

    @Transactional
    public Long save(PostsSaveRequestDto reqDto) {
        return postsRepo.save(reqDto.toEntity()).getId();
    }
}
