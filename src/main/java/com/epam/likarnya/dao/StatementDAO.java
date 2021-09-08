package com.epam.likarnya.dao;

import com.epam.likarnya.model.Patient;
import com.epam.likarnya.model.Statement;

public interface StatementDAO {
    long createStatement(Statement.PatientStatus status, Long patientId);
}
