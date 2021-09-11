package com.epam.likarnya.dao;

import com.epam.likarnya.DTO.DoctorDTO;
import com.epam.likarnya.DTO.NurseDTO;
import com.epam.likarnya.exception.DaoException;
import com.epam.likarnya.model.Patient;
import com.epam.likarnya.model.User;

import java.util.List;

public interface UserDAO {
    User getUserByEmail(String email) throws DaoException;
    long createMedicalWorker(User user) throws DaoException;
    List<DoctorDTO> findDoctorsByCategoryId(Long id) throws DaoException;
    List<DoctorDTO> getDoctors() throws DaoException;
    List<NurseDTO> getNurses() throws DaoException;
    List<DoctorDTO> getDoctorsWithCountOfPatients() throws DaoException;
    List<DoctorDTO> getDoctorsWithCountOfPatientsByCategoryId(Long id) throws DaoException;
}
