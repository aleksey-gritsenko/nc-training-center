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


//	private final BookPostgreDAO bookPostgreDAO;
//
//	@Autowired
//	public BookService(BookPostgreDAO bookPostgreDAO) {
//		this.bookPostgreDAO = bookPostgreDAO;
//	}
//
//	public Book createBook(String title, String header, String author, String overview,
//						   String status, int photoId, int fileId) {
//		Book book = new Book(title, header, author, overview, status, photoId, fileId, genre);
//		bookPostgreDAO.createBook(book);
//
//		return book;
//	}
//
//	public Book updateBook(int bookId, String title, String header, String author,
//						   String overview, String status, int photoId, int fileId){
//		Book book = bookPostgreDAO.getBookById(bookId);
//
//		if (book != null) {
//			book.setTitle(title);
//			book.setHeader(header);
//			book.setAuthor(author);
//			book.setOverview(overview);
//			book.setStatus(status);
//			book.setPhotoId(photoId);
//			book.setFileId(fileId);
//			bookPostgreDAO.updateBook(book, bookId);
//			return book;
//		} else {
//			return null;
//		}
//	}
//<<<<<<< HEAD
//=======
////=======
//	private final BookPostgreDAO bookPostgreDAO;
//	private final FilterCriterionQuery filterCriterionQuery;
//
//	@Autowired
//	public BookService(BookPostgreDAO bookPostgreDAO,FilterCriterionQuery filterCriterionQuery) {
//		this.bookPostgreDAO = bookPostgreDAO;
//		this.filterCriterionQuery = filterCriterionQuery;
//
//	}
//>>>>>>> 5e8f7985b1827f2253e072a1798cc1dc3eff8321
//	private final BookPostgreDAO bookPostgreDAO;
//	private final FilterCriterionQuery filterCriterionQuery;
//
//	@Autowired
//	public BookService(BookPostgreDAO bookPostgreDAO,FilterCriterionQuery filterCriterionQuery) {
//		this.bookPostgreDAO = bookPostgreDAO;
//		this.filterCriterionQuery = filterCriterionQuery;
//
//	}
//
//	public Book createBook(String title, String header, String author, String overview,
//
//						   String status, int photoId, int fileId, String genre) {
//		Book book = new Book(title, header, author, overview, status, photoId, fileId, genre);
//		bookPostgreDAO.createBook(book);
//
//		return book;
//	}
//  public List<Book>  findBookByTitle(String name){
//    return bookPostgreDAO.getBooksByTitle(name);
//
//  }
//	public Book updateBook(int bookId, String title, String header, String author,
//						   String overview, String status, int photoId, int fileId, String genre){
//		Book book = bookPostgreDAO.getBookById(bookId);
//
//		if (book != null) {
//			book.setTitle(title);
//			book.setHeader(header);
//			book.setAuthor(author);
//			book.setOverview(overview);
//			book.setStatus(status);
//			book.setPhotoId(photoId);
//			book.setFileId(fileId);
//			book.setGenre(genre);
//			bookPostgreDAO.updateBook(book, bookId);
//			return book;
//		} else {
//			return null;
//		}
//	}
//
//	public List<Book> getAllBooks(){
//		return bookPostgreDAO.getAllBooks();
//	}
//
//<<<<<<< HEAD
//=======
//
//	public List<Book> filterBooks(String header,ArrayList<String> genre,
//                                ArrayList<String>  author){
//	  filterCriterionQuery.setAuthor(author);
//	  filterCriterionQuery.setGenre(genre);
//	  filterCriterionQuery.setHeader(header);
//	  return bookPostgreDAO.filterBooks(filterCriterionQuery);
//  }
//>>>>>>> 6370a182546e95818761b382343272ba4d99af66
//>>>>>>> 5e8f7985b1827f2253e072a1798cc1dc3eff8321
//	public List<Book> filterBooks(String header,ArrayList<String> genre,
//                                ArrayList<String>  author){
//	  filterCriterionQuery.setAuthor(author);
//	  filterCriterionQuery.setGenre(genre);
//	  filterCriterionQuery.setHeader(header);
//	  return bookPostgreDAO.filterBooks(filterCriterionQuery);
//  }
//<<<<<<< HEAD

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

//=======
//>>>>>>> 5e8f7985b1827f2253e072a1798cc1dc3eff8321
}
