package ua.com.nc.nctrainingproject.persistance.mappers;


import org.springframework.jdbc.core.RowMapper;
import ua.com.nc.nctrainingproject.models.Book;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.BookQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {
  @Override
  public Book mapRow(ResultSet resultSet, int i) throws SQLException {
    Book book = new Book();
    book.setId(resultSet.getInt(BookQuery.BOOK_ID));
    book.setHeader(resultSet.getString(BookQuery.HEADER));
    book.setOverview(resultSet.getString(BookQuery.OVERVIEW));
    book.setPhotoId(resultSet.getInt(BookQuery.PHOTO));
    book.setFileId(resultSet.getInt(BookQuery.FILE));
    book.setStatus(resultSet.getString(BookQuery.STATUS));
    book.setGenre(resultSet.getString(BookQuery.GENRE));
    // book.setId(resultSet.getInt(BookQuery.BOOK_ID));
    return book;
  }





//=======
//	@Override
//	public Book mapRow(ResultSet resultSet, int i) throws SQLException {
//		Book book = new Book();
//		book.setTitle(resultSet.getString(BookQuery.TITLE));
//		book.setHeader(resultSet.getString(BookQuery.HEADER));
//		book.setAuthor(resultSet.getString(BookQuery.AUTHOR));
//		book.setOverview(resultSet.getString(BookQuery.OVERVIEW));
//		book.setPhotoId(resultSet.getInt(BookQuery.PHOTO));
//		book.setFileId(resultSet.getInt(BookQuery.FILE));
//		book.setStatus(resultSet.getString(BookQuery.STATUS));
//
//		book.setGenre(resultSet.getString(BookQuery.GENRE));
//		return book;
//	}
//>>>>>>> 6370a182546e95818761b382343272ba4d99af66
}
