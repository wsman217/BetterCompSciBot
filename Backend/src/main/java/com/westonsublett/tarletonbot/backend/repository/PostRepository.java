package com.westonsublett.tarletonbot.backend.repository;

import com.westonsublett.tarletonbot.backend.data.ForumPost;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<ForumPost, Long> {
}
