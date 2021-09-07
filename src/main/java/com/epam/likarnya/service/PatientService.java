package com.epam.likarnya.service;

import com.epam.likarnya.exception.ServiceException;
import com.epam.likarnya.model.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> getPatientWithOutMedicalCard() throws ServiceException;
}
