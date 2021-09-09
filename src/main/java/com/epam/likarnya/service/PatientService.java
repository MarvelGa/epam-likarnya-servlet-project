package com.epam.likarnya.service;

import com.epam.likarnya.DTO.PatientDTO;
import com.epam.likarnya.DTO.TreatmentPatientDTO;
import com.epam.likarnya.exception.ServiceException;
import com.epam.likarnya.model.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> getPatientWithOutMedicalCard() throws ServiceException;
    Patient getPatientById(Long id) throws ServiceException;
    List<PatientDTO> getPatientsByDoctorId(Long id) throws ServiceException;
    List<TreatmentPatientDTO> getPatientsForTreatmentByDoctorId(Long id) throws ServiceException;
    List<TreatmentPatientDTO> getPatientsHistoryByDoctorId(Long id)throws ServiceException;
}
