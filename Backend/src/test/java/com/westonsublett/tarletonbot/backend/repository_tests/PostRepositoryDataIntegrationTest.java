package com.westonsublett.tarletonbot.backend.repository_tests;

import com.westonsublett.tarletonbot.backend.data.Category;
import com.westonsublett.tarletonbot.backend.data.Post;
import com.westonsublett.tarletonbot.backend.data.User;
import com.westonsublett.tarletonbot.backend.repository.CategoryRepository;
import com.westonsublett.tarletonbot.backend.repository.PostRepository;
import com.westonsublett.tarletonbot.backend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class PostRepositoryDataIntegrationTest {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    private final Category absoluteCategory = new Category("This is a title.",
            "This is a description.");
    private final User absoluteUser = new User("wsman217", 290952158969462795L,
            new Timestamp(Date.valueOf(LocalDate.now()).getTime()));
    private final String absoluteTitle = "This is the title of the post.";
    private final String absoluteContent = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor " +
            "incididunt ut labore et dolore magna aliqua. Sem integer vitae justo eget magna fermentum iaculis eu non. " +
            "Massa id neque aliquam vestibulum morbi blandit. Eget magna fermentum iaculis eu non. Ut faucibus pulvinar " +
            "elementum integer enim neque. \n\nSed pulvinar proin gravida hendrerit lectus. Posuere lorem ipsum dolor sit " +
            "amet consectetur. Sagittis purus sit amet volutpat consequat mauris. Adipiscing at in tellus integer " +
            "feugiat scelerisque varius morbi enim. Orci porta non pulvinar neque laoreet suspendisse interdum consectetur.";
    private final Timestamp absoluteTime = new Timestamp(Date.valueOf(LocalDate.now()).getTime());
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
        assert (foundPost.getTime().equals(absoluteTime));
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

        Post foundPost = postRepository.findPostByTitle(absoluteTitle);

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

        Post post = new Post(absoluteCategory, absoluteUser, absoluteTitle, absoluteContent, absoluteTime);

        postRepository.save(post);
    }
}
