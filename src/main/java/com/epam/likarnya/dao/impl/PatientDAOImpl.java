package com.epam.likarnya.dao.impl;

import com.epam.likarnya.dao.PatientDAO;
import com.epam.likarnya.dao.dbmanager.DBManager;
import com.epam.likarnya.exception.DaoException;
import com.epam.likarnya.exception.Messages;
import com.epam.likarnya.model.Patient;
import com.epam.likarnya.model.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientDAOImpl implements PatientDAO {
    private static final Logger logger = Logger.getLogger(PatientDAOImpl.class);
    private static final String GET_NEW_PATIENT_WITHOUT_M_CARD = "SELECT p.id, p.first_name, p.last_name, p.gender, p.birth_day FROM patients p WHERE p.id NOT IN (SELECT st.patient_id FROM statements st WHERE st.patient_status='NEW' OR st.patient_status='DISCHARGED' OR st.patient_status='DIAGNOSED')";
    private static final String GET_PATIENT_BY_ID = "SELECT * FROM patients WHERE id=?";

    @Override
    public List<Patient> getPatientWithMedicCard() {
        final String query = GET_NEW_PATIENT_WITHOUT_M_CARD;
        List<Patient> patients = new ArrayList<>();
        DBManager dbm;
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            dbm = DBManager.getInstance();
            con = dbm.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                patients.add(extractPatientFromResultSet(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            logger.error(Messages.ERR_CANNOT_READ_ALL_PATIENTS, ex);
            throw new DaoException(Messages.ERR_CANNOT_READ_ALL_PATIENTS, ex);
        } finally {
            DBManager.close(con, stmt, rs);
        }
        return patients;
    }

    @Override
    public Patient findById(Long id) {
        Patient patient = null;
        DBManager dbm;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            dbm = DBManager.getInstance();
            con = dbm.getConnection();
            pstmt = con.prepareStatement(GET_PATIENT_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                patient = extractPatientFromResultSet(rs);
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            logger.error(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, ex);
            throw new DaoException(Messages.ERR_CANNOT_OBTAIN_USER_BY_EMAIL, ex);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        return patient;
    }

    private Patient extractPatientFromResultSet(ResultSet rs) throws SQLException {
        Patient patient = new Patient();
        patient.setId(rs.getLong("id"));
        patient.setFirstName(rs.getString("first_name"));
        patient.setLastName(rs.getString("last_name"));
        patient.setDateOfBirth(LocalDate.parse(rs.getString("birth_day")));
        patient.setGender(Patient.Gender.valueOf(rs.getString("gender")));
        return patient;
    }
}
