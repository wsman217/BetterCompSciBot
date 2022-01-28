package com.westonsublett.tarletonbot.backend.data;

import lombok.Getter;
import org.springframework.data.annotation.Id;
//import org.springframework.data.relational.core.mapping.Table;

//@Table("posts")
public class ForumPost {

    @Id
    @Getter
    private int id;
    @Getter
    private ForumCategory forumCategory;
}
