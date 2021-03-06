package com.epam.likarnya.service;

import com.epam.likarnya.DTO.MedicalCardDTO;
import com.epam.likarnya.exception.ServiceException;
import com.epam.likarnya.model.Statement;

public interface MedicalCardService {
    long createMedicalCard(Statement statement, Long doctorId, String complaints) throws ServiceException;
    MedicalCardDTO getMedicalCardByPatientId(Long patientId) throws ServiceException;;
}
