package com.epam.likarnya.service.impl;

import com.epam.likarnya.dao.PatientDAO;
import com.epam.likarnya.service.PatientService;
import com.epam.likarnya.service.UserService;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return instance;
    }

    private final UserService userService = new UserServiceImpl();

    private PatientService patientService = new PatientServiceImpl();

    public UserService getUserService() {
        return userService;
    }

    public PatientService getPatientService() {
        return patientService;
    }
}
