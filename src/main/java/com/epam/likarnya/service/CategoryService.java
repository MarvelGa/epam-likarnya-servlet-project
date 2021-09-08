package com.epam.likarnya.service;

import com.epam.likarnya.exception.ServiceException;
import com.epam.likarnya.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories() throws ServiceException;
}
