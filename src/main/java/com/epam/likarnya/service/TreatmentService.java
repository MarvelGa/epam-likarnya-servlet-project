package com.epam.likarnya.service;

import com.epam.likarnya.DTO.MedicalCardDTO;
import com.epam.likarnya.exception.ServiceException;
import com.epam.likarnya.model.Statement;
import com.epam.likarnya.model.Treatment;

public interface TreatmentService {
    long setTreatmentAndDiagnosis(Treatment treatment, MedicalCardDTO medicalCard, String diagnosis) throws ServiceException;
    boolean executeTreatment(Long doctorId, Long treatmentId, Long statementId) throws ServiceException;
}
