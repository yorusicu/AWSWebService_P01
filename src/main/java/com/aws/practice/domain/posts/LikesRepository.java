package com.aws.practice.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, LikesPK> {
}
