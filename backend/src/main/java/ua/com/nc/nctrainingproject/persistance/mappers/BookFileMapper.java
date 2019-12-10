package ua.com.nc.nctrainingproject.persistance.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.com.nc.nctrainingproject.models.BookFile;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.BookFileManagementQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookFileMapper implements RowMapper<BookFile> {

	@Override
	public BookFile mapRow(ResultSet resultSet, int i) throws SQLException {
		BookFile bookFile = new BookFile();

		bookFile.setBookId(resultSet.getInt(BookFileManagementQuery.BOOK_ID));
		bookFile.setFile(resultSet.getBytes(BookFileManagementQuery.FILE));
		return bookFile;
	}
}
