package com.westonsublett.tarletonbot.backend.data;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

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
    private User user;

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

    public Post(Category category, User user, String title, String content, Timestamp time) {
        this.category = category;
        this.user = user;
        this.title = title;
        this.content = content;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Post[id=" + id + ", forumCategory=" + category + ", user=" + user + ", title=" + title + ", content=" + content + ", time=" + time + "]";
    }
}
