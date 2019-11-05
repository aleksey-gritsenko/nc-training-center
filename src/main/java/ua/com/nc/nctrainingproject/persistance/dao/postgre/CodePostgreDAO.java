package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.persistance.dao.CodeRecoverDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.CodeRecoverQuery;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.UserQuery;
import ua.com.nc.nctrainingproject.persistance.mappers.CodeRowMapper;
import ua.com.nc.nctrainingproject.persistance.mappers.UserRowMapper;

import java.util.Date;


@Repository
public class CodePostgreDAO implements CodeRecoverDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void createCode(String code,String userName){
        jdbcTemplate.update(CodeRecoverQuery.CREATE_CODE,code,new Date()
                ,userName);
    }

    public String getCodeByUserName(String userName) {
        try {
            return jdbcTemplate.queryForObject
                    (CodeRecoverQuery.GET_CODE_BY_USERNAME, new Object[]{userName}, new CodeRowMapper())
                    .getCode();
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }
    public void deleteByUserName(String userName){
        jdbcTemplate.update(CodeRecoverQuery.DELETE_CODE_BY_USERNAME,userName);
    }

}
