package com.westonsublett.tarletonbot.backend;

import com.westonsublett.tarletonbot.backend.data.User;
import com.westonsublett.tarletonbot.backend.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testUser() {
        String absoluteName = "wsman217";
        Long absoluteDiscordId = 290952158969462795L;
        Timestamp absoluteTime = new Timestamp(Date.valueOf(LocalDate.now()).getTime());
        User testUser = new User(absoluteName, absoluteDiscordId, absoluteTime);
        userRepository.save(testUser);

        User foundUser = userRepository.getUserByDiscordId(absoluteDiscordId);

        assert (foundUser.getName().equals(absoluteName));
        assert (foundUser.getDiscordId().equals(absoluteDiscordId));
        assert (foundUser.getTime().equals(absoluteTime));

        assert (((List <User>)userRepository.findAll()).size() == 1);

        userRepository.delete(foundUser);

        assert (((List <User>)userRepository.findAll()).size() == 0);
    }
}
