package com.epam.likarnya.dao.impl;

import com.epam.likarnya.dao.UserDAO;
import com.epam.likarnya.dao.dbmanager.DBManager;
import com.epam.likarnya.exception.DaoException;
import com.epam.likarnya.exception.Messages;
import com.epam.likarnya.model.Patient;
import com.epam.likarnya.model.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;

public class UserDAOImpl implements UserDAO {
    private static final Logger logger = LogManager.getLogger(UserDAOImpl.class);
    private static final String GET_USER_BY_EMAIL = "SELECT * FROM users WHERE email=?";
    private static final String CREAT_PATIENT = "INSERT INTO patients(first_name, last_name, birth_day, gender) VALUES (?, ?, ?, ?);";
    private static final String CREAT_USER = "INSERT INTO patients(email, first_name, last_name, password, role_id) VALUES (?, ?, ?, ?, ?);";

    @Override
    public User getUserByEmail(String email) throws DaoException {
        User user = null;
        DBManager dbm;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            dbm = DBManager.getInstance();
            con = dbm.getConnection();
            pstmt = con.prepareStatement(GET_USER_BY_EMAIL);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = extractUserFromResultSet(rs);
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            logger.error(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, ex);
            throw new DaoException(Messages.ERR_CANNOT_OBTAIN_USER_BY_EMAIL, ex);
        } finally {
            DBManager.close(con, pstmt, rs);
        }
        return user;
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
    public long createMedicalWorker(User user) throws DaoException {
        return 0;
    }

//    @Override
//    public int createUser(User user) throws DaoException {
//        int id = -1;
//        final String query = CREAT_USER;
//        DBManager dbm;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        Connection con = null;
//        try {
//            dbm = DBManager.getInstance();
//            con = dbm.getConnection();
//            pstmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
//            pstmt.setString(1, user.getEmail());
//            pstmt.setString(2, user.getFirstName());
//            pstmt.setString(3, user.getLastName());
//            pstmt.setString(4, user.getPassword());
//            pstmt.setInt(5, 2);
//
//            pstmt.executeUpdate();
//            rs = pstmt.getGeneratedKeys();
//            if (rs != null && rs.next()) {
//                id = rs.getInt(1);
//            }
//            con.commit();
//        } catch (SQLException ex) {
//            DBManager.rollback(con);
//            logger.error(Messages.ERR_CANNOT_INSERT_USER, ex);
//            throw new DaoException(Messages.ERR_CANNOT_INSERT_USER, ex);
//        } finally {
//            DBManager.close(con, pstmt, rs);
//        }
//        return id;
//    }

    private User extractUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong(1));
        user.setEmail(rs.getString(2));
        user.setFirstName(rs.getString(3));
        user.setLastName(rs.getString(4));
        user.setPassword(rs.getString(5));
        user.setRole(User.Role.valueOf(rs.getString(6)));
        user.setCategoryId(rs.getLong(7));
        return user;
    }
}
