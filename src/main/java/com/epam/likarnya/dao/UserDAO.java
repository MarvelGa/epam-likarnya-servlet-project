package com.epam.likarnya.dao;

import com.epam.likarnya.exception.DaoException;
import com.epam.likarnya.model.User;

public interface UserDAO {
    User getUserByEmail(String email) throws DaoException;

    int createUser(User user) throws DaoException;
}
