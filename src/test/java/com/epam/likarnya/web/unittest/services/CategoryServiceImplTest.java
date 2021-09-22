package com.epam.likarnya.web.unittest.services;

import com.epam.likarnya.dao.CategoryDAO;
import com.epam.likarnya.model.Category;
import com.epam.likarnya.service.CategoryService;
import com.epam.likarnya.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CategoryServiceImplTest {

    @Mock
    final CategoryDAO categoryDAO = mock(CategoryDAO.class);

    CategoryService categoryService;

    private Category category;
    private List<Category> categoryList;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        categoryService= new CategoryServiceImpl(categoryDAO);

        category = new Category();
        category.setId(1L);
        category.setTitle("SURGEON");

        categoryList = new ArrayList<>();
        categoryList.add(category);
    }

    @Test
    void shouldFindAll() {
        try {
            when(categoryService.getAllCategories()).thenReturn(categoryList);
            List<Category> expected = categoryList;
            List<Category> actual = categoryService.getAllCategories();
            assertEquals(expected, actual);
        } catch (Exception e) {
            fail();
        }
    }
}
