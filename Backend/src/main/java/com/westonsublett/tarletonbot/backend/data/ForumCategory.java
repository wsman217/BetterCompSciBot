package com.westonsublett.tarletonbot.backend.data;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "categories")
@Entity
public class ForumCategory implements Serializable {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Getter
    private String title;

    @Getter
    private String description;

    public ForumCategory() {

    }

    public ForumCategory(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category[id=" + id + ", title=" + title + ", description=" + description + "]";
    }
}
