package com.westonsublett.tarletonbot.backend.repository;

import com.westonsublett.tarletonbot.backend.data.ForumCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<ForumCategory, Long> {
}
