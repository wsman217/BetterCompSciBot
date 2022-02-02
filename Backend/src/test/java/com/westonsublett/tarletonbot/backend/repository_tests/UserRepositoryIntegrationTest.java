package com.westonsublett.tarletonbot.backend.repository_tests;

import com.westonsublett.tarletonbot.backend.data.Users;
import com.westonsublett.tarletonbot.backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserRepositoryIntegrationTest {

    private final String absoluteName = "wsman217";
    private final Long absoluteDiscordId = 290952158969462795L;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void addUser() {
        addTestUser();

        assert (((List<Users>) userRepository.findAll()).size() == 1);
    }

    @Test
    public void getUserByDiscordId() {
        addTestUser();

        Users foundUser = userRepository.findByDiscordId(absoluteDiscordId);

        assert (foundUser.getName().equals(absoluteName));
        assert (foundUser.getDiscordId().equals(absoluteDiscordId));
    }

    @Test
    public void deleteUser() {
        addTestUser();

        Users foundUser = userRepository.findByDiscordId(absoluteDiscordId);

        userRepository.delete(foundUser);

        assert (((List<Users>) userRepository.findAll()).size() == 0);
    }

    @Test
    public void deleteUserByDiscordId() {
        addTestUser();

        userRepository.deleteByDiscordId(absoluteDiscordId);

        assert (((List<Users>) userRepository.findAll()).size() == 0);
    }

    private void addTestUser() {
        userRepository.deleteAll();
        Users testUser = new Users(absoluteName, absoluteDiscordId);
        userRepository.save(testUser);
    }
}
