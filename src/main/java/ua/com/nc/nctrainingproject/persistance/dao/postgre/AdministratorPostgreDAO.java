package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.Admin;
import ua.com.nc.nctrainingproject.persistance.dao.AdministratorDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.AdminQuery;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.UserQuery;
import ua.com.nc.nctrainingproject.persistance.mappers.AdminRowMapper;

import java.util.List;


@Repository
public class AdministratorPostgreDAO implements AdministratorDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Admin getAdministratorByName(String name) {
        try {
        return jdbcTemplate.query(AdminQuery.GET_ADMIN_BY_NAME, new Object[]{name}, new AdminRowMapper()).get(0);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    public void updatePassword(String password,String agminName){
        jdbcTemplate.update(AdminQuery.UPDATE_PASSWORD,password,agminName);
    }
    @Override
    public void createAdministrator(Admin admin) {
        jdbcTemplate.update(AdminQuery.CREATE_ADMIN, admin.getUserName(), admin.getUserPassword(), admin.getEmail(), "admin");
    }

    public String getAdminEmailByAdminName(String adminName,String email){

        List<Admin> result =    jdbcTemplate.query(AdminQuery.GET_EMAIL_BY_NAME,
                new AdminRowMapper(),adminName,email);
        if(result.size() == 0){
            return null;
        }
        return result.get(0).getEmail();
    }



}
