package com.epam.likarnya.dao.impl;

import com.epam.likarnya.DTO.PatientDTO;
import com.epam.likarnya.DTO.TreatmentPatientDTO;
import com.epam.likarnya.dao.PatientDAO;
import com.epam.likarnya.dao.dbmanager.DBManager;
import com.epam.likarnya.exception.DaoException;
import com.epam.likarnya.exception.Messages;
import com.epam.likarnya.model.Patient;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientDAOImpl implements PatientDAO {
    private static final Logger logger = Logger.getLogger(PatientDAOImpl.class);

    private static final String CREAT_PATIENT = "INSERT INTO patients(first_name, last_name, birth_day, gender) VALUES (?, ?, ?, ?);";

    private static final String GET_NEW_PATIENT_WITHOUT_M_CARD = "SELECT p.id, p.first_name, p.last_name, p.gender, p.birth_day" +
            " FROM patients p WHERE p.id NOT IN (SELECT st.patient_id FROM statements st WHERE st.patient_status='NEW' OR st.patient_status='DISCHARGED' OR st.patient_status='DIAGNOSED')";

    private static final String GET_PATIENT_BY_ID = "SELECT * FROM patients WHERE id=?";

    private static final String GET_PATIENTS_BY_DOCTOR_ID = "SELECT p.id AS id," +
            " p.first_name AS firstName," +
            " p.last_name AS lastName," +
            " p.birth_day as dateOfBirth," +
            " p.gender AS gender," +
            " mc.complaints as complaints" +
            " FROM patients p, statements st, medical_cards mc" +
            " WHERE p.id=st.patient_id AND mc.statement_id=st.id AND st.patient_status='NEW' " +
            " AND p.id IN (SELECT st.patient_id FROM statements st, medical_cards mc, users u WHERE st.id=mc.statement_id AND mc.doctor_id=u.id AND st.patient_status='NEW' AND u.id=?);";

    private static final String GET_PATIENTS_FOR_TREATMENT = "SELECT p.id AS id," +
            " p.first_name AS firstName," +
            " p.last_name AS lastName," +
            " p.birth_day as dateOfBirth," +
            " p.gender AS gender," +
            " mc.complaints as complaints," +
            " mc.diagnosis AS diagnosis," +
            " tr.appointment AS appointment," +
            " tr.appointment_status AS appointmentStatus," +
            " u.first_name AS doctorFirstName," +
            " u.last_name AS doctorLastName," +
            " c.title AS doctorCategory," +
            " tr.id AS treatmentId," +
            " st.id AS statementId" +
            " FROM patients p, statements st, medical_cards mc, treatments tr, users u, categories c" +
            " WHERE c.id=u.category_id AND u.id=mc.doctor_id AND p.id=st.patient_id AND mc.id=tr.m_card_id\n" +
            " AND mc.statement_id =st.id AND p.id NOT IN (SELECT st.patient_id FROM statements st WHERE st.patient_status='DISCHARGED') \n" +
            " AND p.id IN (SELECT st.patient_id FROM statements st, medical_cards mc, users u, treatments tr WHERE mc.id=tr.m_card_id AND st.id=mc.statement_id AND mc.doctor_id=u.id AND tr.appointment_status='NOT_EXECUTED' AND st.patient_status='DIAGNOSED' AND u.id=?);";

    private static final String GET_PATIENTS_HISTORY_BY_DOCTOR_ID = " SELECT p.id AS id,\n" +
            " p.first_name AS firstName,\n" +
            " p.last_name AS lastName,\n" +
            " p.birth_day as dateOfBirth,\n" +
            " p.gender AS gender,\n" +
            " mc.complaints as complaints,\n" +
            " mc.diagnosis AS diagnosis,\n" +
            " tr.appointment AS appointment,\n" +
            " tr.appointment_status AS appointmentStatus,\n" +
            " u.first_name AS doctorFirstName,\n" +
            " u.last_name AS doctorLastName,\n" +
            " c.title AS doctorCategory,\n" +
            " (SELECT u.first_name FROM users u WHERE u.id=tr.executor_id) as nameOfExecutor,\n" +
            " (SELECT u.last_name FROM users u WHERE u.id=tr.executor_id) as lastNameOfExecutor,\n" +
            " (SELECT u.role FROM users u WHERE u.id=tr.executor_id) as roleOfExecutor,\n" +
            " st.created_at AS dateOfAdmission,\n"+
            " st.changed AS dateOfDischarge\n"+
            " FROM patients p, statements st, medical_cards mc, treatments tr, users u, categories c \n" +
            " WHERE c.id=u.category_id AND u.id=mc.doctor_id AND p.id=st.patient_id AND mc.id=tr.m_card_id AND mc.statement_id =st.id\n" +
            " AND p.id IN (SELECT st.patient_id FROM statements st, medical_cards mc, users u, treatments tr \n" +
            " WHERE mc.id=tr.m_card_id AND st.id=mc.statement_id AND mc.doctor_id=u.id AND tr.appointment_status='EXECUTED' AND st.patient_status='DISCHARGED' AND u.id=?);";

    private static final String GET_PATIENTS_FOR_TREATMENT_FOR_NURSE = " SELECT p.id AS id,\n" +
            " p.first_name AS firstName,\n" +
            " p.last_name AS lastName,\n" +
            " p.birth_day as dateOfBirth,\n" +
            " p.gender AS gender,\n" +
            " mc.complaints as complaints,\n" +
            " mc.diagnosis AS diagnosis,\n" +
            " tr.appointment AS appointment,\n" +
            " tr.appointment_status AS appointmentStatus,\n" +
            " u.first_name AS doctorFirstName,\n" +
            " u.last_name AS doctorLastName,\n" +
            " c.title AS doctorCategory,\n" +
            " tr.id AS treatmentId,\n" +
            " st.id AS statementId\n" +
            " FROM patients p, statements st, medical_cards mc, treatments tr, users u, categories c\n" +
            " WHERE c.id=u.category_id AND u.id=mc.doctor_id AND p.id=st.patient_id AND mc.id=tr.m_card_id\n" +
            " AND mc.statement_id =st.id AND p.id NOT IN (SELECT st.patient_id FROM statements st WHERE st.patient_status='DISCHARGED') \n" +
            " AND p.id IN (SELECT st.patient_id FROM statements st, medical_cards mc, users u, treatments tr WHERE mc.id=tr.m_card_id AND st.id=mc.statement_id \n" +
            " AND mc.doctor_id=u.id AND tr.appointment_status='NOT_EXECUTED' AND st.patient_status='DIAGNOSED' AND (tr.appointment='DRUG' OR tr.appointment='PROCEDURE'));";


    private static final String GET_NURSE_TREATMENT_HISTORY = " SELECT p.id AS id,\n" +
            " p.first_name AS firstName,\n" +
            " p.last_name AS lastName,\n" +
            " p.birth_day as dateOfBirth,\n" +
            " p.gender AS gender,\n" +
            " mc.complaints as complaints,\n" +
            " mc.diagnosis AS diagnosis,\n" +
            " tr.appointment AS appointment,\n" +
            " tr.appointment_status AS appointmentStatus,\n" +
            " u.first_name AS doctorFirstName,\n" +
            " u.last_name AS doctorLastName,\n" +
            " c.title AS doctorCategory,\n" +
            " (SELECT u.first_name FROM users u WHERE u.id=tr.executor_id) as nameOfExecutor,\n" +
            " (SELECT u.last_name FROM users u WHERE u.id=tr.executor_id) as lastNameOfExecutor,\n" +
            " (SELECT u.role FROM users u WHERE u.id=tr.executor_id) as roleOfExecutor,\n" +
            " st.created_at AS dateOfAdmission,\n"+
            " st.changed AS dateOfDischarge\n"+
            " FROM patients p, statements st, medical_cards mc, treatments tr, users u, categories c \n" +
            " WHERE c.id=u.category_id AND u.id=mc.doctor_id AND p.id=st.patient_id AND mc.id=tr.m_card_id AND mc.statement_id =st.id\n" +
            " AND p.id IN (SELECT st.patient_id FROM statements st, medical_cards mc, users u, treatments tr \n" +
            " WHERE mc.id=tr.m_card_id AND st.id=mc.statement_id AND mc.doctor_id=u.id AND tr.appointment_status='EXECUTED' AND st.patient_status='DISCHARGED' AND tr.executor_id=?);";

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

    @Override
    public List<PatientDTO> getPatientsByDoctorId(Long id) {
        final String query = GET_PATIENTS_BY_DOCTOR_ID;
        List<PatientDTO> patients = new ArrayList<>();
        DBManager dbm;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            dbm = DBManager.getInstance();
            con = dbm.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                PatientDTO patientDTO = new PatientDTO();
                patientDTO.setId(rs.getLong("id"));
                patientDTO.setFirstName(rs.getString("firstName"));
                patientDTO.setLastName(rs.getString("lastName"));
                patientDTO.setDateOfBirth(rs.getString("dateOfBirth"));
                patientDTO.setGender(rs.getString("gender"));
                patientDTO.setComplaints(rs.getString("complaints"));
                patients.add(patientDTO);
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
    public List<TreatmentPatientDTO> getPatientsForTreatmentByDoctorId(Long id) {
        final String query = GET_PATIENTS_FOR_TREATMENT;
        List<TreatmentPatientDTO> patients = new ArrayList<>();
        DBManager dbm;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            dbm = DBManager.getInstance();
            con = dbm.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                patients.add(extractPatientForTreatmentFromResultSet(rs));
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
    public List<TreatmentPatientDTO> getPatientsHistoryByDoctorId(Long id) {
        final String query = GET_PATIENTS_HISTORY_BY_DOCTOR_ID;
        List<TreatmentPatientDTO> patients = new ArrayList<>();
        DBManager dbm;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            dbm = DBManager.getInstance();
            con = dbm.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                patients.add(extractPatientHistoryFromResultSet(rs));
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
    public long createPatient(Patient patient) throws DaoException {
        int id = -1;
        final String query = CREAT_PATIENT;
        DBManager dbm;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            dbm = DBManager.getInstance();
            con = dbm.getConnection();
            pstmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, patient.getFirstName());
            pstmt.setString(2, patient.getLastName());
            pstmt.setDate(3, Date.valueOf(patient.getDateOfBirth()));
            pstmt.setString(4, String.valueOf(patient.getGender()));

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs != null && rs.next()) {
                id = rs.getInt(1);
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            logger.error(Messages.ERR_CANNOT_INSERT_USER, ex);
            throw new DaoException(Messages.ERR_CANNOT_INSERT_USER, ex);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        return id;
    }

    @Override
    public List<TreatmentPatientDTO> getPatientsForTreatmentByNurse() {
        final String query = GET_PATIENTS_FOR_TREATMENT_FOR_NURSE;
        List<TreatmentPatientDTO> patients = new ArrayList<>();
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
                patients.add(extractPatientForTreatmentFromResultSet(rs));
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
    public List<TreatmentPatientDTO> getNurseTreatmentHistoryById(Long id) {
        final String query = GET_NURSE_TREATMENT_HISTORY;
        List<TreatmentPatientDTO> patients = new ArrayList<>();
        DBManager dbm;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            dbm = DBManager.getInstance();
            con = dbm.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                patients.add(extractPatientHistoryFromResultSet(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            logger.error(Messages.ERR_CANNOT_READ_NURSE_TREATMENT_HISTORY_OF_PATIENTS, ex);
            throw new DaoException(Messages.ERR_CANNOT_READ_NURSE_TREATMENT_HISTORY_OF_PATIENTS, ex);
        } finally {
            DBManager.close(con, stmt, rs);
        }
        return patients;
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

    private TreatmentPatientDTO extractPatientForTreatmentFromResultSet(ResultSet rs) throws SQLException {
        TreatmentPatientDTO patientForTreatment = new TreatmentPatientDTO();
        patientForTreatment.setId(rs.getLong("id"));
        patientForTreatment.setFirstName(rs.getString("firstName"));
        patientForTreatment.setLastName(rs.getString("lastName"));
        patientForTreatment.setDateOfBirth(rs.getString("dateOfBirth"));
        patientForTreatment.setGender(rs.getString("gender"));
        patientForTreatment.setComplaints(rs.getString("complaints"));
        patientForTreatment.setDiagnosis(rs.getString("diagnosis"));
        patientForTreatment.setAppointment(rs.getString("appointment"));
        patientForTreatment.setAppointmentStatus(rs.getString("appointmentStatus"));
        patientForTreatment.setDoctorFirstName(rs.getString("doctorFirstName"));
        patientForTreatment.setDoctorLastName(rs.getString("doctorLastName"));
        patientForTreatment.setDoctorCategory(rs.getString("doctorCategory"));
        patientForTreatment.setTreatmentId(rs.getString("treatmentId"));
        patientForTreatment.setStatementId(rs.getString("statementId"));
        return patientForTreatment;
    }

    private TreatmentPatientDTO extractPatientHistoryFromResultSet(ResultSet rs) throws SQLException {
        TreatmentPatientDTO patientForTreatment = new TreatmentPatientDTO();
        patientForTreatment.setId(rs.getLong("id"));
        patientForTreatment.setFirstName(rs.getString("firstName"));
        patientForTreatment.setLastName(rs.getString("lastName"));
        patientForTreatment.setDateOfBirth(rs.getString("dateOfBirth"));
        patientForTreatment.setGender(rs.getString("gender"));
        patientForTreatment.setComplaints(rs.getString("complaints"));
        patientForTreatment.setDiagnosis(rs.getString("diagnosis"));
        patientForTreatment.setAppointment(rs.getString("appointment"));
        patientForTreatment.setAppointmentStatus(rs.getString("appointmentStatus"));
        patientForTreatment.setDoctorFirstName(rs.getString("doctorFirstName"));
        patientForTreatment.setDoctorLastName(rs.getString("doctorLastName"));
        patientForTreatment.setDoctorCategory(rs.getString("doctorCategory"));
        patientForTreatment.setNameOfExecutor(rs.getString("nameOfExecutor"));
        patientForTreatment.setLastNameOfExecutor(rs.getString("lastNameOfExecutor"));
        patientForTreatment.setRoleOfExecutor(rs.getString("roleOfExecutor"));
        patientForTreatment.setDateOfAdmission(rs.getTimestamp("dateOfAdmission").toLocalDateTime());
        patientForTreatment.setDateOfDischarge(rs.getTimestamp("dateOfDischarge").toLocalDateTime());
        return patientForTreatment;
    }
}
