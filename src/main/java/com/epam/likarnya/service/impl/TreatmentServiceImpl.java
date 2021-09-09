package com.epam.likarnya.service.impl;

import com.epam.likarnya.DTO.MedicalCardDTO;
import com.epam.likarnya.dao.TreatmentDAO;
import com.epam.likarnya.dao.UserDAO;
import com.epam.likarnya.dao.impl.DaoFactory;
import com.epam.likarnya.exception.DaoException;
import com.epam.likarnya.exception.Messages;
import com.epam.likarnya.exception.ServiceException;
import com.epam.likarnya.model.Treatment;
import com.epam.likarnya.service.TreatmentService;
import org.apache.log4j.Logger;

public class TreatmentServiceImpl implements TreatmentService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private TreatmentDAO treatmentDAO = daoFactory.getTreatmentDAO();
    private static final Logger logger = Logger.getLogger(TreatmentServiceImpl.class);

    @Override
    public long setTreatmentAndDiagnosis(Treatment treatment, MedicalCardDTO medicalCard, String diagnosis) throws ServiceException {
        long inserted;
        try {
            inserted = treatmentDAO.setTreatmentAndDiagnosis(treatment, medicalCard, diagnosis);
        } catch (DaoException e) {
            logger.error(Messages.ERR_SERVICE_LAYER_CANNOT_CREATE_TREATMENT_AND_SET_DIAGNOSIS, e);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_CREATE_TREATMENT_AND_SET_DIAGNOSIS, e);
        }
        return inserted;
    }

    @Override
    public boolean executeTreatment(Long doctorId, Long treatmentId, Long statementId) throws ServiceException {
        boolean executed;
        try {
            executed = treatmentDAO.executeTreatment(doctorId, treatmentId, statementId);
        } catch (DaoException e) {
            logger.error(Messages.ERR_SERVICE_LAYER_CANNOT_EXECUTE_TREATMENT, e);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_EXECUTE_TREATMENT, e);
        }
        return executed;
    }
}
