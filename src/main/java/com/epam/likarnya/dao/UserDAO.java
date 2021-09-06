package com.epam.likarnya.dao;

import com.epam.likarnya.exception.DaoException;
import com.epam.likarnya.model.Patient;
import com.epam.likarnya.model.User;

public interface UserDAO {
    User getUserByEmail(String email) throws DaoException;

    long createPatient(Patient patient) throws DaoException;
    long createMedicalWorker(User user) throws DaoException;
}
