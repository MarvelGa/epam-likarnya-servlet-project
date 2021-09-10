package com.epam.likarnya.service;

import com.epam.likarnya.DTO.DoctorDTO;
import com.epam.likarnya.DTO.NurseDTO;
import com.epam.likarnya.exception.DaoException;
import com.epam.likarnya.exception.ServiceException;
import com.epam.likarnya.model.Patient;
import com.epam.likarnya.model.User;

import java.util.List;

public interface UserService {
    User getUserByEmail (String email) throws ServiceException;
    long addMedicalWorker(User user) throws ServiceException;
    List<DoctorDTO> getDoctorsByCategoryId(Long id) throws ServiceException;
    List<DoctorDTO> getDoctors() throws ServiceException;
    List<NurseDTO> getNurses() throws ServiceException;
}
