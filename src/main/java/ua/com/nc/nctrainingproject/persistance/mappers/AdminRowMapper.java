package ua.com.nc.nctrainingproject.persistance.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.com.nc.nctrainingproject.models.Admin;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.AdminQuery;

import java.sql.ResultSet;
import java.sql.SQLException;


public class AdminRowMapper implements RowMapper<Admin> {
    @Override
    public Admin mapRow(ResultSet resultSet, int i) throws SQLException {
        Admin admin = new Admin();
        admin.setUserName(resultSet.getString(AdminQuery.NAME));
        admin.setUserPassword(resultSet.getString(AdminQuery.PASSWORD));
        admin.setEmail(resultSet.getString(AdminQuery.EMAIL));
        admin.setAdminRole(resultSet.getString(AdminQuery.ROLE));
        return admin;
    }
}
