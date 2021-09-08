package com.epam.likarnya.service.impl;

import com.epam.likarnya.dao.CategoryDAO;
import com.epam.likarnya.dao.impl.DaoFactory;
import com.epam.likarnya.model.Category;
import com.epam.likarnya.service.CategoryService;
import org.apache.log4j.Logger;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private CategoryDAO categoryDAO = daoFactory.getCategoryDao();
    private static final Logger logger = Logger.getLogger(CategoryServiceImpl.class);

    @Override
    public List<Category> getAllCategories() {
        return categoryDAO.findAll();
    }
}
