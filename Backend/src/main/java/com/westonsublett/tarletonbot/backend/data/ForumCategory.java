package com.westonsublett.tarletonbot.backend.data;

import lombok.Getter;
import org.springframework.data.annotation.Id;
//import org.springframework.data.relational.core.mapping.Table;

//@Table("categories")
public class ForumCategory {

    @Getter
    @Id
    private long id;
    @Getter
    private String title;
    @Getter
    private String description;

    public ForumCategory(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category[id=" + id + ", title=" + title + ", description=" + description + "]";
    }
}
