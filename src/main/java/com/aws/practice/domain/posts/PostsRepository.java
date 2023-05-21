package com.aws.practice.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {
//    @Query("SELECT p FROM Posts p LEFT JOIN FETCH p.Repls")
//    List<Posts> findAllWithRepls();
}
