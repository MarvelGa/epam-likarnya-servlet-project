package com.epam.likarnya.dao.impl;

import com.epam.likarnya.DTO.MedicalCardDTO;
import com.epam.likarnya.dao.MedicalCardDAO;
import com.epam.likarnya.dao.dbmanager.DBManager;
import com.epam.likarnya.exception.DaoException;
import com.epam.likarnya.exception.Messages;
import com.epam.likarnya.model.Statement;
import org.apache.log4j.Logger;

import java.sql.*;

public class MedicalCardDAOImpl implements MedicalCardDAO {
    private static final Logger logger = Logger.getLogger(MedicalCardDAOImpl.class);
    private static final String CREATE_STATEMENT="INSERT INTO statements (created_at, patient_status, patient_id) VALUES (?,?,?);";
    private static final String CREATE_MEDIC_CARD="INSERT INTO medical_cards (complaints, statement_id, doctor_id) VALUES (?,?,?);";
    private static final String FIND_MEDIC_CARD_BY_PATIENT_ID_AND_STATUS_NEW="SELECT * FROM medical_cards mc WHERE mc.id=(SELECT mc.id FROM medical_cards mc, statements st, patients p WHERE st.id=mc.statement_id AND st.patient_id=p.id AND st.patient_status='NEW' AND p.id=?);";

    @Override
    public long createMedicalCard(Statement statement, Long doctorId, String complaints) {
        int medicCardId =-1;
        int statementId =-1;

        DBManager dbm;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            dbm = DBManager.getInstance();
            con = dbm.getConnection();
            con.setAutoCommit(false);

            pstmt = con.prepareStatement(CREATE_STATEMENT, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setTimestamp(1, Timestamp.valueOf(statement.getCreatedAt()));
            pstmt.setString(2, String.valueOf(statement.getPatientStatus()));
            pstmt.setLong(3, statement.getPatient().getId());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs != null && rs.next()) {
                statementId = rs.getInt(1);
            }
            pstmt = con.prepareStatement(CREATE_MEDIC_CARD, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, complaints);
            pstmt.setLong(2, statementId);
            pstmt.setLong(3, doctorId);
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs != null && rs.next()) {
                medicCardId = rs.getInt(1);
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            logger.error(Messages.ERR_CANNOT_CREATE_MEDIC_CARD, ex);
            throw new DaoException(Messages.ERR_CANNOT_CREATE_MEDIC_CARD, ex);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        return medicCardId;
    }

    @Override
    public MedicalCardDTO getMedicalCardByPatientId(Long patientId) {
        MedicalCardDTO medicalCard = null;
        DBManager dbm;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            dbm = DBManager.getInstance();
            con = dbm.getConnection();
            pstmt = con.prepareStatement(FIND_MEDIC_CARD_BY_PATIENT_ID_AND_STATUS_NEW);
            pstmt.setLong(1, patientId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                medicalCard = extractMedicalCardFromResultSet(rs);
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            logger.error(Messages.ERR_CANNOT_OBTAIN_MEDICAL_CARD_BY_PATIENT_ID, ex);
            throw new DaoException(Messages.ERR_CANNOT_OBTAIN_MEDICAL_CARD_BY_PATIENT_ID, ex);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        return medicalCard;
    }

    private MedicalCardDTO extractMedicalCardFromResultSet(ResultSet rs) throws SQLException {
        MedicalCardDTO extractedMedicalCard = new MedicalCardDTO();
        extractedMedicalCard.setId(rs.getLong("id"));
        extractedMedicalCard.setStatementId(rs.getLong("statement_id"));
        extractedMedicalCard.setDoctorId(rs.getLong("doctor_id"));
        extractedMedicalCard.setComplaints(rs.getString("complaints"));
        extractedMedicalCard.setDiagnosis(rs.getString("diagnosis"));
        return extractedMedicalCard;
    }
}
