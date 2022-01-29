package com.westonsublett.tarletonbot.backend.data;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "posts")
public class ForumPost implements Serializable {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Getter
    @ManyToOne
    @JoinColumn(name = "category_id")
    private ForumCategory forumCategory;

    @Getter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Getter
    private String title;

    @Getter
    private String content;

    @Getter
    private Timestamp time;

    public ForumPost() {

    }

    public ForumPost(Long id, ForumCategory forumCategory, User user, String title, String content, Timestamp time) {
        this.id = id;
        this.forumCategory = forumCategory;
        this.user = user;
        this.title = title;
        this.content = content;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Post[id=" + id + ", forumCategory=" + forumCategory + ", user=" + user + ", title=" + title + ", content=" + content + ", time=" + time + "]";
    }
}
