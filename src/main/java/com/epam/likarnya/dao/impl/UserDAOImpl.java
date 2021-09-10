package com.epam.likarnya.dao.impl;

import com.epam.likarnya.DTO.DoctorDTO;
import com.epam.likarnya.DTO.NurseDTO;
import com.epam.likarnya.dao.UserDAO;
import com.epam.likarnya.dao.dbmanager.DBManager;
import com.epam.likarnya.exception.DaoException;
import com.epam.likarnya.exception.Messages;
import com.epam.likarnya.model.Patient;
import com.epam.likarnya.model.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static final Logger logger = LogManager.getLogger(UserDAOImpl.class);
    private static final String GET_USER_BY_EMAIL = "SELECT * FROM users WHERE email=?";
    private static final String CREAT_DOCTOR = "INSERT INTO users(first_name, last_name, email, password, `role`, category_id) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String CREAT_NURSE = "INSERT INTO users(first_name, last_name, email, password, `role`) VALUES (?, ?, ?, ?, ?);";
    private static final String GET_DOCTORS_BY_CATEGORY = "SELECT u.id, u.first_name, u.last_name, u.role, cat.title AS category FROM users u INNER JOIN categories cat ON u.category_id=cat.id WHERE u.role='DOCTOR' AND cat.id=?;";
    private static final String GET_ALL_DOCTORS ="SELECT u.id, u.first_name, u.last_name, u.role, cat.title AS category\n" +
            "FROM users u INNER JOIN categories cat ON u.category_id=cat.id WHERE u.role='DOCTOR';";
    private static final String GET_ALL_NURSES ="SELECT u.id, u.first_name, u.last_name, u.role \n" +
            "FROM users u WHERE u.role='NURSE';";

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
    public long createMedicalWorker(User user) throws DaoException {
        int id = -1;
        final String query;
        if (user.getCategoryId() != null) {
            query = CREAT_DOCTOR;
        } else {
            query = CREAT_NURSE;
        }
        DBManager dbm;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            dbm = DBManager.getInstance();
            con = dbm.getConnection();
            pstmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, String.valueOf(user.getRole()));
            if (user.getCategoryId() != null) {
                pstmt.setLong(6, user.getCategoryId());
            }
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
    public List<DoctorDTO> findDoctorsByCategoryId(Long id) throws DaoException {
        final String query = GET_DOCTORS_BY_CATEGORY;
        List<DoctorDTO> doctors = new ArrayList<>();
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
                DoctorDTO doctorDTO = new DoctorDTO();
                doctorDTO.setId(rs.getLong("id"));
                doctorDTO.setFirstName(rs.getString("first_name"));
                doctorDTO.setLastName(rs.getString("last_name"));
                doctorDTO.setRole(User.Role.valueOf(rs.getString("role")));
                doctorDTO.setCategory(rs.getString("category"));
                doctors.add(doctorDTO);
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            logger.error(Messages.ERR_CANNOT_READ_ALL_PATIENTS, ex);
            throw new DaoException(Messages.ERR_CANNOT_READ_ALL_PATIENTS, ex);
        } finally {
            DBManager.close(con, stmt, rs);
        }
        return doctors;
    }

    @Override
    public List<DoctorDTO> getDoctors() throws DaoException {
        final String query = GET_ALL_DOCTORS;
        List<DoctorDTO> doctors = new ArrayList<>();
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
                doctors.add(extractDoctorFromResultSet(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            logger.error(Messages.ERR_CANNOT_READ_ALL_DOCTORS, ex);
            throw new DaoException(Messages.ERR_CANNOT_READ_ALL_DOCTORS, ex);
        } finally {
            DBManager.close(con, stmt, rs);
        }
        return doctors;
    }

    @Override
    public List<NurseDTO> getNurses() throws DaoException {
        final String query = GET_ALL_NURSES;
        List<NurseDTO> nurses = new ArrayList<>();
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
                nurses.add(extractNurseFromResultSet(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            logger.error(Messages.ERR_CANNOT_READ_ALL_NURSES, ex);
            throw new DaoException(Messages.ERR_CANNOT_READ_ALL_NURSES, ex);
        } finally {
            DBManager.close(con, stmt, rs);
        }
        return nurses;
    }

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

    private DoctorDTO extractDoctorFromResultSet(ResultSet rs) throws SQLException {
        DoctorDTO doctor = new DoctorDTO();
        doctor.setId(rs.getLong(1));
        doctor.setFirstName(rs.getString("first_name"));
        doctor.setLastName(rs.getString("last_name"));
        doctor.setRole(User.Role.valueOf(rs.getString("role")));
        doctor.setCategory(rs.getString("category"));
        return doctor;
    }

    private NurseDTO extractNurseFromResultSet(ResultSet rs) throws SQLException {
        NurseDTO nurse = new NurseDTO();
        nurse.setId(rs.getLong(1));
        nurse.setFirstName(rs.getString("first_name"));
        nurse.setLastName(rs.getString("last_name"));
        nurse.setRole(User.Role.valueOf(rs.getString("role")));
        return nurse;
    }

}
