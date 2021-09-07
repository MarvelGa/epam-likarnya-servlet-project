package com.epam.likarnya.service.impl;

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
}
