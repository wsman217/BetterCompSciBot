package com.westonsublett.tarletonbot.backend.data;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
public class User {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Getter
    @Column(name = "name")
    private String name;

    @Getter
    @Column(nullable = false, unique = true, name = "discord_id")
    private Long discordId;

    @Getter
    @Column(name = "time")
    private Timestamp time;

    public User() {

    }

    public User(String name, Long discordId, Timestamp time) {
        this.name = name;
        this.discordId = discordId;
        this.time = time;
    }

    @Override
    public String toString() {
        return "User[id=" + id + ", name=" + name + ", discordId=" + discordId + ", time=" + time + "]";
    }
}
