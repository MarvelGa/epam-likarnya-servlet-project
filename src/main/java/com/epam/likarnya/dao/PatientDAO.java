package com.epam.likarnya.dao;

import com.epam.likarnya.DTO.PatientDTO;
import com.epam.likarnya.model.Patient;

import java.util.List;

public interface PatientDAO {
   List<Patient> getPatientWithMedicCard();
   Patient findById(Long id);
   List<PatientDTO> getPatientsByDoctorId(Long id);
}
