package com.westonsublett.tarletonbot.backend.data;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * This class represents an SQL table named categories with the schema of
 * id BIGINT, title VARCHAR(60), description VARCHAR(255)
 */
@Table(name = "categories")
@Entity
public class Category implements Serializable {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Getter
    @Column(name = "title")
    private String title;

    @Getter
    @Column(name = "description")
    private String description;

    public Category() {

    }

    public Category(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category[id=" + id + ", title=" + title + ", description=" + description + "]";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Category && (obj == this || obj.toString().equals(this.toString()));
    }
}
