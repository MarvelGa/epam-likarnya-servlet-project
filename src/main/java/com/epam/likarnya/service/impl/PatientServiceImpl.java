package com.epam.likarnya.service.impl;

import com.epam.likarnya.DTO.PatientDTO;
import com.epam.likarnya.DTO.TreatmentPatientDTO;
import com.epam.likarnya.dao.PatientDAO;
import com.epam.likarnya.dao.impl.DaoFactory;
import com.epam.likarnya.exception.DaoException;
import com.epam.likarnya.exception.Messages;
import com.epam.likarnya.exception.ServiceException;
import com.epam.likarnya.model.Patient;
import com.epam.likarnya.service.PatientService;
import org.apache.log4j.Logger;

import java.util.List;

public class PatientServiceImpl implements PatientService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private PatientDAO patientDAO = daoFactory.getPatientDao();
    private static final Logger logger = Logger.getLogger(PatientServiceImpl.class);

    public PatientServiceImpl() {
    }

    public PatientServiceImpl(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    @Override
    public List<Patient> getPatientWithOutMedicalCard() throws ServiceException {
        List<Patient> patients;
        try {
            return patients = patientDAO.getPatientWithMedicCard();
        } catch (DaoException e) {
            logger.error(Messages.ERR_SERVICE_LAYER_CANNOT_READ_ALL_PATIENT, e);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_READ_ALL_PATIENT, e);
        }
    }

    @Override
    public Patient getPatientById(Long id) throws ServiceException {
        Patient patient;
        try {
            return patient = patientDAO.findById(id);
        }catch (DaoException e){
            logger.error(Messages.ERR_SERVICE_LAYER_CANNOT_READ_PATIENT_BY_ID, e);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_READ_PATIENT_BY_ID, e);
        }

    }

    @Override
    public List<PatientDTO> getPatientsByDoctorId(Long id) throws ServiceException {
        List<PatientDTO> patients;
        try {
            return patients = patientDAO.getPatientsByDoctorId(id);
        } catch (DaoException e) {
            logger.error(Messages.ERR_SERVICE_LAYER_CANNOT_FIND_PATIENTS_BY_DOCTOR_ID, e);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_FIND_PATIENTS_BY_DOCTOR_ID, e);
        }
    }

    @Override
    public List<TreatmentPatientDTO> getPatientsForTreatmentByDoctorId(Long id) throws ServiceException {
        List<TreatmentPatientDTO> patients;
        try {
            return patients = patientDAO.getPatientsForTreatmentByDoctorId(id);
        } catch (DaoException e) {
            logger.error(Messages.ERR_SERVICE_LAYER_CANNOT_FIND_PATIENTS_BY_DOCTOR_ID, e);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_FIND_PATIENTS_BY_DOCTOR_ID, e);
        }
    }

    @Override
    public List<TreatmentPatientDTO> getPatientsHistoryByDoctorId(Long id) throws ServiceException {
        List<TreatmentPatientDTO> patients;
        try {
            return patients = patientDAO.getPatientsHistoryByDoctorId(id);
        } catch (DaoException e) {
            logger.error(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_PATIENTS_HISTORY_BY_DOCTOR_ID, e);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_PATIENTS_HISTORY_BY_DOCTOR_ID, e);
        }
    }

    @Override
    public long addPatient(Patient patient) throws ServiceException {
        long inserted;
        try {
            inserted =patientDAO.createPatient(patient);
        } catch (DaoException e) {
            logger.error(Messages.ERR_SERVICE_LAYER_CANNOT_INSERT_USER, e);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_INSERT_USER, e);
        }
        return inserted;
    }

    @Override
    public List<TreatmentPatientDTO> getPatientsForTreatmentByNurse() throws ServiceException {
        List<TreatmentPatientDTO> patients;
        try {
            return patients = patientDAO.getPatientsForTreatmentByNurse();
        } catch (DaoException e) {
            logger.error(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_PATIENTS_FOR_TREATMENT_BY_NURSE, e);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_PATIENTS_FOR_TREATMENT_BY_NURSE, e);
        }
    }
}
