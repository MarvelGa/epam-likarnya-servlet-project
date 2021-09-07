package com.epam.likarnya.dao.impl;

import com.epam.likarnya.dao.PatientDAO;
import com.epam.likarnya.dao.UserDAO;

public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();

    public static DaoFactory getInstance() {
        return instance;
    }

    private final UserDAO userDao = new UserDAOImpl();
    private final PatientDAO patientDao = new PatientDAOImpl();

    public UserDAO getUserDao() {
        return userDao;
    }

    public PatientDAO getPatientDao() {
        return patientDao;
    }
}
