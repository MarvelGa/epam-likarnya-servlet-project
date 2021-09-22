package com.epam.likarnya.service.impl;

import com.epam.likarnya.dao.CategoryDAO;
import com.epam.likarnya.dao.impl.DaoFactory;
import com.epam.likarnya.exception.DaoException;
import com.epam.likarnya.exception.Messages;
import com.epam.likarnya.exception.ServiceException;
import com.epam.likarnya.model.Category;
import com.epam.likarnya.service.CategoryService;
import org.apache.log4j.Logger;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private CategoryDAO categoryDAO = daoFactory.getCategoryDao();
    private static final Logger logger = Logger.getLogger(CategoryServiceImpl.class);

    public CategoryServiceImpl() {
    }

    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public List<Category> getAllCategories() throws ServiceException {
        List<Category> categories;
        try {
            categories = categoryDAO.findAll();
        } catch (DaoException e) {
            logger.error(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_CATEGORIES, e);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_CATEGORIES, e);
        }
        return categories;
    }
}
