package com.epam.likarnya.service.impl;

import com.epam.likarnya.dao.MedicalCardDAO;
import com.epam.likarnya.dao.UserDAO;
import com.epam.likarnya.dao.impl.DaoFactory;
import com.epam.likarnya.exception.DaoException;
import com.epam.likarnya.exception.Messages;
import com.epam.likarnya.exception.ServiceException;
import com.epam.likarnya.model.Statement;
import com.epam.likarnya.service.MedicalCardService;
import org.apache.log4j.Logger;

public class MedicalCardServiceImpl implements MedicalCardService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private MedicalCardDAO medicalCardDAO = daoFactory.getMedicCardDAO();
    private static final Logger logger = Logger.getLogger(MedicalCardServiceImpl.class);

    @Override
    public long createMedicalCard(Statement statement, Long doctorId, String complaints) throws ServiceException {
        long medicalCardId;
        try {
            medicalCardId = medicalCardDAO.createMedicalCard(statement, doctorId, complaints);
        } catch (DaoException e) {
            logger.error(Messages.ERR_SERVICE_LAYER_CANNOT_CREATE_MEDICAL_CARD, e);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_CREATE_MEDICAL_CARD, e);
        }
        return medicalCardId;
    }
}
