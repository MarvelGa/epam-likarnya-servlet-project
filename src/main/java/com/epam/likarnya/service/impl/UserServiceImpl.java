package com.epam.likarnya.service.impl;

import com.epam.likarnya.dao.UserDAO;
import com.epam.likarnya.dao.impl.DaoFactory;
import com.epam.likarnya.exception.DaoException;
import com.epam.likarnya.exception.Messages;
import com.epam.likarnya.exception.ServiceException;
import com.epam.likarnya.model.Patient;
import com.epam.likarnya.model.User;
import com.epam.likarnya.service.UserService;
import org.apache.log4j.Logger;

public class UserServiceImpl implements UserService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private UserDAO userDAO = daoFactory.getUserDao();
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    public UserServiceImpl() {
    }

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    public User getUserByEmail(String email) throws ServiceException {
        User user;
        try {
            user = userDAO.getUserByEmail(email);
            return user;
        } catch (DaoException ex) {
            logger.error(ex);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_USER_BY_EMAIL, ex);
        }
    }

    @Override
    public long addPatient(Patient patient) throws ServiceException {
        long inserted;
        try {
            inserted = userDAO.createPatient(patient);
        } catch (DaoException e) {
            logger.error(Messages.ERR_SERVICE_LAYER_CANNOT_INSERT_USER, e);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_INSERT_USER, e);
        }
        return inserted;
    }

    @Override
    public long addMedicalWorker(User user) throws ServiceException {
        return 0;
    }
}
