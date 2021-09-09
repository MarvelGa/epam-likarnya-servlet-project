package com.epam.likarnya.dao.impl;

import com.epam.likarnya.DTO.MedicalCardDTO;
import com.epam.likarnya.dao.TreatmentDAO;
import com.epam.likarnya.dao.dbmanager.DBManager;
import com.epam.likarnya.exception.DaoException;
import com.epam.likarnya.exception.Messages;
import com.epam.likarnya.model.Treatment;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;

public class TreatmentDAOImpl implements TreatmentDAO {
    private static final Logger logger = Logger.getLogger(TreatmentDAOImpl.class);
    private static final String CREATE_TREATMENT = "INSERT INTO treatments (appointment, appointment_status, created_at, m_card_id) VALUES (?,?,?,?);";
    private static final String UPDATE_DIAGNOSIS_IN_MEDIC_CARD = "UPDATE medical_cards mc SET mc.diagnosis=? WHERE mc.id=?;";
    private static final String UPDATE_STATEMENT = "UPDATE statements st SET st.patient_status=?, st.changed=? WHERE st.id=?;";
    private static final String UPDATE_TREATMENT = "UPDATE treatments tr SET tr.appointment_status=?, tr.changed=?, tr.executor_id=? WHERE tr.id=?;";

    @Override
    public long setTreatmentAndDiagnosis(Treatment treatment, MedicalCardDTO medicalCard, String diagnosis) {
        int treatmentId = -1;

        DBManager dbm;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            dbm = DBManager.getInstance();
            con = dbm.getConnection();
            con.setAutoCommit(false);

            pstmt = con.prepareStatement(CREATE_TREATMENT, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, String.valueOf(treatment.getAppointment()));
            pstmt.setString(2, String.valueOf(treatment.getAppointmentStatus()));
            pstmt.setTimestamp(3, Timestamp.valueOf(treatment.getCreatedAt()));
            pstmt.setLong(4, medicalCard.getId());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs != null && rs.next()) {
                treatmentId = rs.getInt(1);
            }

            pstmt = con.prepareStatement(UPDATE_DIAGNOSIS_IN_MEDIC_CARD);
            pstmt.setString(1, diagnosis);
            pstmt.setLong(2, medicalCard.getId());
            pstmt.executeUpdate();

            pstmt = con.prepareStatement(UPDATE_STATEMENT);
            pstmt.setString(1, "DIAGNOSED");
            pstmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setLong(3, medicalCard.getStatementId());
            pstmt.executeUpdate();

            con.commit();

        } catch (SQLException ex) {
            DBManager.rollback(con);
            logger.error(Messages.ERR_CANNOT_CREATE_TREATMENT_AND_SET_DIAGNOSIS, ex);
            throw new DaoException(Messages.ERR_CANNOT_CREATE_TREATMENT_AND_SET_DIAGNOSIS, ex);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        return treatmentId;
    }

    @Override
    public boolean executeTreatment(Long doctorId, Long treatmentId, Long statementId) {
        boolean executed = false;

        DBManager dbm;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            dbm = DBManager.getInstance();
            con = dbm.getConnection();
            con.setAutoCommit(false);

            pstmt = con.prepareStatement(UPDATE_TREATMENT);
            pstmt.setString(1, "EXECUTED");
            pstmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setLong(3, doctorId);
            pstmt.setLong(4, treatmentId);
            pstmt.executeUpdate();

            pstmt = con.prepareStatement(UPDATE_STATEMENT);
            pstmt.setString(1, "DISCHARGED");
            pstmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setLong(3, statementId);
            pstmt.executeUpdate();

            con.commit();
            executed=true;
        } catch (SQLException ex) {
            DBManager.rollback(con);
            logger.error(Messages.ERR_CANNOT_CREATE_TREATMENT_AND_SET_DIAGNOSIS, ex);
            throw new DaoException(Messages.ERR_CANNOT_CREATE_TREATMENT_AND_SET_DIAGNOSIS, ex);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        return executed;
    }
}
