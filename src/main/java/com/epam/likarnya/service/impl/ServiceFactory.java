package com.epam.likarnya.service.impl;

import com.epam.likarnya.dao.PatientDAO;
import com.epam.likarnya.service.*;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return instance;
    }

    private final UserService userService = new UserServiceImpl();

    private final PatientService patientService = new PatientServiceImpl();

    private final CategoryService categoryService = new CategoryServiceImpl();

    private final MedicalCardService medicalCardService = new MedicalCardServiceImpl();

    private final TreatmentService treatmentService = new TreatmentServiceImpl();

    public UserService getUserService() {
        return userService;
    }

    public PatientService getPatientService() {
        return patientService;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public MedicalCardService getMedicalCardService() {
        return medicalCardService;
    }

    public TreatmentService getTreatmentService() {
        return treatmentService;
    }
}
