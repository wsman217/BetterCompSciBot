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
    private String name;

    @Getter
    private Long discordId;

    @Getter
    private Timestamp time;

    public User() {

    }

    public User(Long id, String name, Long discordId, Timestamp time) {
        this.id = id;
        this.name = name;
        this.discordId = discordId;
        this.time = time;
    }

    @Override
    public String toString() {
        return "User[id=" + id + ", name=" + name + ", discordId=" + discordId + ", time=" + time + "]";
    }
}
