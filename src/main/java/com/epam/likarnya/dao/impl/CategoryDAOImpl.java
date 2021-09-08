package com.epam.likarnya.dao.impl;

import com.epam.likarnya.dao.CategoryDAO;
import com.epam.likarnya.dao.dbmanager.DBManager;
import com.epam.likarnya.exception.DaoException;
import com.epam.likarnya.exception.Messages;
import com.epam.likarnya.model.Category;
import com.epam.likarnya.model.Patient;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    private static final Logger logger = LogManager.getLogger(CategoryDAOImpl.class);
    private static final String GET_ALL_CATEGORY = "SELECT * FROM categories";

    @Override
    public List<Category> findAll() {
        final String query = GET_ALL_CATEGORY;
        List<Category> categories = new ArrayList<>();
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
                categories.add(extractCategoryFromResultSet(rs));
            }
            con.commit();
        } catch (SQLException ex) {
            DBManager.rollback(con);
            logger.error(Messages.ERR_CANNOT_READ_ALL_PATIENTS, ex);
            throw new DaoException(Messages.ERR_CANNOT_READ_ALL_PATIENTS, ex);
        } finally {
            DBManager.close(con, stmt, rs);
        }
        return categories;
    }

    private Category extractCategoryFromResultSet(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setId(rs.getLong("id"));
        category.setTitle(rs.getString("title"));
        return category;
    }
}
