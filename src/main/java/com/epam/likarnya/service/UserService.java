package com.epam.likarnya.service;

import com.epam.likarnya.exception.ServiceException;
import com.epam.likarnya.model.User;

public interface UserService {
    User getUserByEmail (String email) throws ServiceException;
}
