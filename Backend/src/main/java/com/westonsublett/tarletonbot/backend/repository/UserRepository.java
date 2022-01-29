package com.westonsublett.tarletonbot.backend.repository;

import com.westonsublett.tarletonbot.backend.data.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    
    User getUserByDiscordId(Long discordId);
}
