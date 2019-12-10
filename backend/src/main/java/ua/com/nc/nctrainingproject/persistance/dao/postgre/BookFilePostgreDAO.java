package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.Book;
import ua.com.nc.nctrainingproject.models.BookFile;
import ua.com.nc.nctrainingproject.persistance.dao.AbstractDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.BookFileManagementQuery;
import ua.com.nc.nctrainingproject.persistance.mappers.BookFileMapper;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class BookFilePostgreDAO extends AbstractDAO<BookFile> {

	@Autowired
	public BookFilePostgreDAO(DataSource dataSource) {
		super(dataSource);
	}

	public List<BookFile> getFiles() {
		return super.getAllEntities(BookFileManagementQuery.GET_BOOK_FILES, new BookFileMapper());
	}

	public BookFile getBookFile(Book book) {
		return super.getEntityById(BookFileManagementQuery.GET_BOOK_FILE, new BookFileMapper(), book.getId());
	}

	public BookFile deleteFile(BookFile bookFile) {
		super.deleteEntityById(BookFileManagementQuery.DELETE_BOOK_FILE, bookFile.getBookId());
		return bookFile;
	}

	public void addFile(BookFile bookFile) {
		super.create(BookFileManagementQuery.INSERT_BOOK_FILE, new Object[]
				{bookFile.getBookId(), bookFile.getFile()});
	}

	public void updateFile(BookFile bookFile) {
		super.update(BookFileManagementQuery.UPDATE_BOOK_FILE, new Object[]{
				bookFile.getBookId(), bookFile.getFile()});
	}
}
