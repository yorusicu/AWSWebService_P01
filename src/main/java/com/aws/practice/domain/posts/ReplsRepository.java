package com.aws.practice.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplsRepository extends JpaRepository<Repls, ReplsPK> {
    List<Repls> findByPostId(Long postId);
}
