package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.Author;
import ua.com.nc.nctrainingproject.models.Book;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.BookPostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.FilterCriterionQuery;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {


	private final BookPostgreDAO bookPostgreDAO;
	private final FilterCriterionQuery filterCriterionQuery;

	@Autowired
	public BookService(BookPostgreDAO bookPostgreDAO,FilterCriterionQuery filterCriterionQuery) {
		this.bookPostgreDAO = bookPostgreDAO;
		this.filterCriterionQuery = filterCriterionQuery;
	}

	public Book createBook(String header, String overview, String status, String photo, int fileId) {
		Book book = new Book(header, overview, status, photo, fileId);
		bookPostgreDAO.createBook(book);

		return book;
	}
  public Book  getBookById(int bookId){
    return bookPostgreDAO.getBookById(bookId);

  }
	public Book updateBook(int bookId, String header, String overview, String status, String photo, int fileId){
		Book book = bookPostgreDAO.getBookById(bookId);

		if (book != null) {
			book.setHeader(header);
			book.setOverview(overview);
			book.setStatus(status);
			book.setPhotoId(photo);
			book.setFileId(fileId);
			bookPostgreDAO.updateBookById(bookId,book);
			return book;
		} else {
			return null;
		}
	}

	public List<Book> getAllBooks(){
		return bookPostgreDAO.getAllBooks();
	}
/*
	public List<Book> filterBooks(String header,ArrayList<String> genre,
                                ArrayList<String>  author){
	  filterCriterionQuery.setAuthor(author);
	  filterCriterionQuery.setGenre(genre);
	  filterCriterionQuery.setHeader(header);
	  return bookPostgreDAO.filterBooks(filterCriterionQuery);
  }
*/
}
