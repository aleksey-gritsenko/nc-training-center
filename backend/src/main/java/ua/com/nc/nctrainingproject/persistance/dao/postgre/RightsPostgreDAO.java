package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.RightsQuery;

@Repository
public class RightsPostgreDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RightsPostgreDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getDescriptionByRightId(int rightId) {

       return jdbcTemplate.queryForObject(RightsQuery.GET_DESCRIPTION_BY_RIGHT_ID, new Object[]{rightId}, String.class);
    }
}
