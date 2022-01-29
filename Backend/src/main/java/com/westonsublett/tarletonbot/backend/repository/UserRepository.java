package com.westonsublett.tarletonbot.backend.repository;

import com.westonsublett.tarletonbot.backend.data.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
