package com.westonsublett.tarletonbot.backend.repository;

import com.westonsublett.tarletonbot.backend.data.Post;
import com.westonsublett.tarletonbot.backend.data.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

    Post findByTitle(String title);
    List<Post> findAllByUser(Users user);
    @Transactional
    void deleteByTitle(String title);
    @Transactional
    void deleteAllByUser(Users user);

}
