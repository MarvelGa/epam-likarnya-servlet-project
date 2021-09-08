package com.epam.likarnya.dao;

import com.epam.likarnya.model.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> findAll();
}
