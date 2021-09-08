package com.epam.likarnya.dao.impl;

import com.epam.likarnya.dao.*;

public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();

    public static DaoFactory getInstance() {
        return instance;
    }

    private final UserDAO userDao = new UserDAOImpl();
    private final PatientDAO patientDao = new PatientDAOImpl();
    private final CategoryDAO categoryDao = new CategoryDAOImpl();
    private final MedicalCardDAO medicCardDAO = new MedicalCardDAOImpl();
    private final TreatmentDAO treatmentDAO = new TreatmentDAOImpl();

    public UserDAO getUserDao() {
        return userDao;
    }

    public PatientDAO getPatientDao() {
        return patientDao;
    }

    public CategoryDAO getCategoryDao() {
        return categoryDao;
    }

    public MedicalCardDAO getMedicCardDAO() {
        return medicCardDAO;
    }

    public TreatmentDAO getTreatmentDAO() {
        return treatmentDAO;
    }
}
