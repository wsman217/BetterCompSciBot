package com.westonsublett.tarletonbot.backend.data;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * This class represents an SQL table named post with the schema of
 * id BIGINT, category_id FOREIGN KEY from category, user_id FOREIGN KEY from users, title VARCHAR(60), content LONGTEXT,
 * time TIMESTAMP
 */
@Entity
@Table(name = "posts")
public class Post implements Serializable {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Getter
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Getter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @Getter
    @Column(name = "title")
    private String title;

    @Getter
    @Column(name = "content")
    private String content;

    @Getter
    @Column(name = "time")
    private Timestamp time;

    public Post() {

    }

    public Post(Category category, Users user, String title, String content) {
        this.category = category;
        this.user = user;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Post[id=" + id + ", forumCategory=" + category + ", user=" + user + ", title=" + title + ", content=" + content + ", time=" + time + "]";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Post && (obj == this || obj.toString().equals(this.toString()));
    }
}
