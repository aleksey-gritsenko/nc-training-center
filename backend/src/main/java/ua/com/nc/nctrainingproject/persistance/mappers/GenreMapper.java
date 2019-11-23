package ua.com.nc.nctrainingproject.persistance.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.com.nc.nctrainingproject.models.Genre;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.GenreQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreMapper implements RowMapper<Genre> {
    @Override
    public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
        Genre genre = new Genre();
        genre.setId(resultSet.getInt(GenreQuery.ID));
        genre.setName(resultSet.getString(GenreQuery.GENRE_NAME));
        return genre;
    }
}
