package com.westonsublett.tarletonbot.backend.controller;

import com.westonsublett.tarletonbot.backend.data.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {

    @GetMapping("{discord_id}")
    public User getUserById(@PathVariable(name="discord_id") Long discordId) {
        return null;
    }
}
