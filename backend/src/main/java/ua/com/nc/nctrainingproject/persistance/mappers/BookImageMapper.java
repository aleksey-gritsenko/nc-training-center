package ua.com.nc.nctrainingproject.persistance.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.com.nc.nctrainingproject.models.BookImage;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.BookFileManagementQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookImageMapper implements RowMapper<BookImage> {

    @Override
    public BookImage mapRow(ResultSet resultSet, int i) throws SQLException {
        BookImage bookImage = new BookImage();

        bookImage.setBookId(resultSet.getInt(BookFileManagementQuery.BOOK_ID));
        bookImage.setImage(resultSet.getBytes(BookFileManagementQuery.FILE));
        return bookImage;
    }
}
