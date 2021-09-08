package com.epam.likarnya.dao.impl;

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
}
