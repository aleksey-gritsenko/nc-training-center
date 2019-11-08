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
        admin.setAdminName(resultSet.getString(AdminQuery.ADMIN_NAME));
        admin.setAdminPassword(resultSet.getString(AdminQuery.ADMIN_PASSWORD));
        admin.setEmail(resultSet.getString(AdminQuery.EMAIL));
        admin.setAdminRole(resultSet.getString(AdminQuery.ADMIN_ROLE));
        return admin;
    }
}
