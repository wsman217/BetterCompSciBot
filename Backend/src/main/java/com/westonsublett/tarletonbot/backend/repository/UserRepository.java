package com.westonsublett.tarletonbot.backend.repository;

import com.westonsublett.tarletonbot.backend.data.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByDiscordId(Long discordId);

    @Transactional
    void deleteByDiscordId(Long discordId);
}
