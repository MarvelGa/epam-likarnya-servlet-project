package com.epam.likarnya.dao;

import com.epam.likarnya.DTO.MedicalCardDTO;
import com.epam.likarnya.exception.ServiceException;
import com.epam.likarnya.model.Treatment;

public interface TreatmentDAO {
    long setTreatmentAndDiagnosis(Treatment treatment, MedicalCardDTO medicalCard, String diagnosis) throws ServiceException;
}
