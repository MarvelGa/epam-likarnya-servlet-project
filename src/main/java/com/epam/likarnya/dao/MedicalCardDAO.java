package com.epam.likarnya.dao;

import com.epam.likarnya.DTO.MedicalCardDTO;
import com.epam.likarnya.model.MedicalCard;
import com.epam.likarnya.model.Statement;

public interface MedicalCardDAO {
   long createMedicalCard(Statement statement, Long doctorId, String complaints);
   MedicalCardDTO getMedicalCardByPatientId(Long patientId);
}

