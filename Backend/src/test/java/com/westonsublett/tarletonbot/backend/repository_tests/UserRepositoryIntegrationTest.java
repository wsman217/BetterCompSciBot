package com.westonsublett.tarletonbot.backend.repository_tests;

import com.westonsublett.tarletonbot.backend.data.User;
import com.westonsublett.tarletonbot.backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    private final String absoluteName = "wsman217";
    private final Long absoluteDiscordId = 290952158969462795L;
    private final Timestamp absoluteTime = new Timestamp(Date.valueOf(LocalDate.now()).getTime());

    @Test
    public void addUser() {
        addTestUser();

        assert (((List<User>) userRepository.findAll()).size() == 1);
    }

    @Test
    public void getUserByDiscordId() {
        addTestUser();

        User foundUser = userRepository.findByDiscordId(absoluteDiscordId);

        assert (foundUser.getName().equals(absoluteName));
        assert (foundUser.getDiscordId().equals(absoluteDiscordId));
        assert (foundUser.getTime().equals(absoluteTime));
    }

    @Test
    public void deleteUser() {
        addTestUser();

        User foundUser = userRepository.findByDiscordId(absoluteDiscordId);

        userRepository.delete(foundUser);

        assert (((List<User>) userRepository.findAll()).size() == 0);
    }

    @Test
    public void deleteUserByDiscordId() {
        addTestUser();

        userRepository.deleteByDiscordId(absoluteDiscordId);

        assert (((List<User>) userRepository.findAll()).size() == 0);
    }

    private void addTestUser() {
        userRepository.deleteAll();
        User testUser = new User(absoluteName, absoluteDiscordId, absoluteTime);
        userRepository.save(testUser);
    }
}
