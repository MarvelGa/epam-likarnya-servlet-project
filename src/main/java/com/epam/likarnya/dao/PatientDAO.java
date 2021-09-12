package com.epam.likarnya.dao;

import com.epam.likarnya.DTO.PatientDTO;
import com.epam.likarnya.DTO.TreatmentPatientDTO;
import com.epam.likarnya.exception.DaoException;
import com.epam.likarnya.model.Patient;

import java.util.List;

public interface PatientDAO {
   List<Patient> getPatientWithMedicCard();
   Patient findById(Long id);
   List<PatientDTO> getPatientsByDoctorId(Long id);
   List<TreatmentPatientDTO> getPatientsForTreatmentByDoctorId(Long id);
   List<TreatmentPatientDTO> getPatientsHistoryByDoctorId(Long id);
   long createPatient(Patient patient) throws DaoException;
   List<TreatmentPatientDTO> getPatientsForTreatmentByNurse();
   List<TreatmentPatientDTO> getNurseTreatmentHistoryById(Long id);
}
