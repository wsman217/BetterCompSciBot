package com.westonsublett.tarletonbot.backend.data;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * This class represents an SQL table named post with the schema of
 * id BIGINT, name VARCHAR(60), discord_id BIGINT, time TIMESTAMP
 */
@Entity
@Table(name = "users")
public class Users {

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

    public Users() {
    }

    public Users(String name, Long discordId) {
        this.name = name;
        this.discordId = discordId;
    }

    @Override
    public String toString() {
        return "User[id=" + id + ", name=" + name + ", discordId=" + discordId + ", time=" + time + "]";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Users && (obj == this || obj.toString().equals(this.toString()));
    }
}
