package com.westonsublett.tarletonbot.backend.repository_tests;

import com.westonsublett.tarletonbot.backend.data.Category;
import com.westonsublett.tarletonbot.backend.data.Post;
import com.westonsublett.tarletonbot.backend.data.Users;
import com.westonsublett.tarletonbot.backend.repository.CategoryRepository;
import com.westonsublett.tarletonbot.backend.repository.PostRepository;
import com.westonsublett.tarletonbot.backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PostRepositoryDataIntegrationTest {

    private final Category absoluteCategory = new Category("This is a title.",
            "This is a description.");
    private final Users absoluteUser = new Users("wsman217", 290952158969462795L);
    private final String absoluteTitle = "This is the title of the post.";
    private final String absoluteContent = "There is no strife, no prejudice, no national conflict in outer space as yet. " +
            "Its hazards are hostile to us all. Its conquest deserves the best of all mankind, and its opportunity for " +
            "peaceful cooperation many never come again. But why, some say, the moon? Why choose this as our goal? And " +
            "they may well ask why climb the highest mountain? Why, 35 years ago, fly the Atlantic? Why does Rice play Texas?" +
            "\n\n\n" +
            "We choose to go to the moon. We choose to go to the moon in this decade and do the other things, not " +
            "because they are easy, but because they are hard, because that goal will serve to organize and measure the " +
            "best of our energies and skills, because that challenge is one that we are willing to accept, one we are " +
            "unwilling to postpone, and one which we intend to win, and the others, too.";
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void addPost() {
        addTestPost();

        assert (((List<Post>) postRepository.findAll()).size() == 1);
    }

    @Test
    public void getPostByTitle() {
        addTestPost();

        Post foundPost = postRepository.findByTitle(absoluteTitle);

        assert (foundPost.getCategory().equals(absoluteCategory));
        assert (foundPost.getUser().equals(absoluteUser));
        assert (foundPost.getTitle().equals(absoluteTitle));
        assert (foundPost.getContent().equals(absoluteContent));
    }

    @Test
    public void getAllPostsByUser() {
        addTestPost();

        List<Post> foundPosts = postRepository.findAllByUser(absoluteUser);

        assert (foundPosts.size() == 1);
    }

    @Test
    public void deletePost() {
        addTestPost();

        Post foundPost = postRepository.findByTitle(absoluteTitle);

        postRepository.delete(foundPost);

        assert (((List<Post>) postRepository.findAll()).size() == 0);
    }

    @Test
    public void deletePostByTitle() {
        addTestPost();

        postRepository.deleteByTitle(absoluteTitle);

        assert (((List<Post>) postRepository.findAll()).size() == 0);
    }

    @Test
    public void deleteAllPostByUser() {
        addTestPost();

        postRepository.deleteAllByUser(absoluteUser);

        assert (((List<Post>) postRepository.findAll()).size() == 0);
    }

    private void addTestPost() {
        postRepository.deleteAll();
        categoryRepository.deleteAll();
        userRepository.deleteAll();

        categoryRepository.save(absoluteCategory);
        userRepository.save(absoluteUser);

        Post post = new Post(absoluteCategory, absoluteUser, absoluteTitle, absoluteContent);

        postRepository.save(post);
    }
}
