package com.epam.likarnya.service.impl;

import com.epam.likarnya.DTO.DoctorDTO;
import com.epam.likarnya.DTO.NurseDTO;
import com.epam.likarnya.dao.UserDAO;
import com.epam.likarnya.dao.impl.DaoFactory;
import com.epam.likarnya.exception.DaoException;
import com.epam.likarnya.exception.Messages;
import com.epam.likarnya.exception.ServiceException;
import com.epam.likarnya.model.User;
import com.epam.likarnya.service.UserService;
import org.apache.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private UserDAO userDAO = daoFactory.getUserDao();
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    public UserServiceImpl() {
    }

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    public User getUserByEmail(String email) throws ServiceException {
        User user;
        try {
            user = userDAO.getUserByEmail(email);
        } catch (DaoException ex) {
            logger.error(ex);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_USER_BY_EMAIL, ex);
        }
        return user;
    }

    @Override
    public long addMedicalWorker(User user) throws ServiceException {
        long inserted;
        try {
            inserted = userDAO.createMedicalWorker(user);
        } catch (DaoException e) {
            logger.error(Messages.ERR_SERVICE_LAYER_CANNOT_INSERT_MEDICAL_WORKER, e);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_INSERT_MEDICAL_WORKER, e);
        }
        return inserted;
    }

    @Override
    public List<DoctorDTO> getDoctorsByCategoryId(Long id) throws ServiceException {
        List<DoctorDTO> doctors;
        try {
            doctors = userDAO.findDoctorsByCategoryId(id);
        } catch (DaoException e) {
            logger.error(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_DOCTORS_BY_CATEGORY_ID, e);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_DOCTORS_BY_CATEGORY_ID, e);
        }
        return doctors;
    }

    @Override
    public List<DoctorDTO> getDoctors() throws ServiceException {
        List<DoctorDTO> doctors;
        try {
            doctors = userDAO.getDoctors();
        } catch (DaoException e) {
            logger.error(Messages.ERR_SERVICE_LAYER_CANNOT_FIND_DOCTORS, e);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_FIND_DOCTORS, e);
        }
        return doctors;
    }

    @Override
    public List<NurseDTO> getNurses() throws ServiceException {
        List<NurseDTO> nurses;
        try {
            nurses = userDAO.getNurses();
        } catch (DaoException e) {
            logger.error(Messages.ERR_SERVICE_LAYER_CANNOT_FIND_NURSES, e);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_FIND_NURSES, e);
        }
        return nurses;
    }

    @Override
    public List<DoctorDTO> getDoctorsWithCountOfPatients() throws ServiceException {
        List<DoctorDTO> doctors;
        try {
            doctors = userDAO.getDoctorsWithCountOfPatients();
        } catch (DaoException e) {
            logger.error(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_DOCTORS_WITH_PATIENT_COUNT, e);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_DOCTORS_WITH_PATIENT_COUNT, e);
        }
        return doctors;
    }

    @Override
    public List<DoctorDTO> getDoctorsWithCountOfPatientsByCategoryId(Long id) throws ServiceException {
        List<DoctorDTO> doctors;
        try {
            doctors = userDAO.getDoctorsWithCountOfPatientsByCategoryId(id);
        } catch (DaoException e) {
            logger.error(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_DOCTORS_WITH_PATIENT_COUNT_BY_CATEGORY_ID, e);
            throw new ServiceException(Messages.ERR_SERVICE_LAYER_CANNOT_OBTAIN_DOCTORS_WITH_PATIENT_COUNT_BY_CATEGORY_ID, e);
        }
        return doctors;
    }
}
