package com.westonsublett.tarletonbot.backend.repository_tests;

import com.westonsublett.tarletonbot.backend.data.Category;
import com.westonsublett.tarletonbot.backend.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CategoryRepositoryIntegrationTest {

    @Autowired
    private CategoryRepository categoryRepository;

    private final String absoluteTitle = "This is title";
    private final String absoluteDescription = "This is a description.";

    @Test
    public void addCategory() {
        addTestCategory();

        assert (((List<Category>) categoryRepository.findAll()).size() == 1);
    }

    @Test
    public void getByTitle() {
        addTestCategory();

        Category category = categoryRepository.findByTitle(absoluteTitle);

        assert (category.getTitle().equals(absoluteTitle));
        assert (category.getDescription().equals(absoluteDescription));
    }

    @Test
    public void remove() {
        addTestCategory();

        Category category = categoryRepository.findByTitle(absoluteTitle);
        categoryRepository.delete(category);

        assert (((List<Category>) categoryRepository.findAll()).size() == 0);
    }

    @Test
    public void removeByTitle() {
        addTestCategory();

        categoryRepository.deleteByTitle(absoluteTitle);

        assert (((List<Category>) categoryRepository.findAll()).size() == 0);
    }

    public void addTestCategory() {
        categoryRepository.deleteAll();

        categoryRepository.save(new Category(absoluteTitle, absoluteDescription));

    }
}
