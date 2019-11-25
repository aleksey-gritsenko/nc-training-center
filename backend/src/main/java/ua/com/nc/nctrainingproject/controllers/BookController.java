package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.Author;
import ua.com.nc.nctrainingproject.models.Book;
import ua.com.nc.nctrainingproject.models.Genre;
import ua.com.nc.nctrainingproject.services.BookService;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/book")
public class BookController {
	private final BookService bookService;

	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@RequestMapping(method = RequestMethod.POST)
	public Book addBook(@RequestParam(name = "header") String header,
						@RequestParam(name = "overview") String overview,
						@RequestParam(name = "photo") int photo,
						@RequestParam(name = "file") int fileId,
						@RequestParam(name = "status") String status) {


		return bookService.createBook(header, overview, status, photo, fileId);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Book updateBook(@RequestParam(name = "bookId") int bookId,
						   @RequestParam(name = "header") String header,
						   @RequestParam(name = "overview") String overview,
						   @RequestParam(name = "photo") int photo,
						   @RequestParam(name = "file") int fileId,
						   @RequestParam(name = "status") String status) {

		return bookService.updateBook(bookId, header, overview, status, photo, fileId);
	}

	@RequestMapping(value = "/id", method = RequestMethod.GET)
	@ResponseBody
	public Book getBookById(@RequestParam(name = "id") int bookId) {
		return bookService.getBookById(bookId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}

	@RequestMapping(value = "/filter", method = RequestMethod.GET)
	public List<Book> filterBook(@RequestParam(name = "header") String header
								 //@RequestParam(name = "genre") ArrayList<String> genres,
								// @RequestParam(name = "author") ArrayList<String> authors
								 ) {
ArrayList<String> genres = new ArrayList<>();
ArrayList<String> authors = new ArrayList<>();
		return bookService.filterBooks(header + "%", genres, authors);
	}
	@RequestMapping(value = "/genres", method = RequestMethod.GET)
	public List<Genre> getAllGenres() {return bookService.getAllGenres();}

	@RequestMapping(value = "/authors", method = RequestMethod.GET)
	public List<Author> getAllAuthors() {return bookService.getAllAuthors();}

	@RequestMapping(value = "/authors/book", method = RequestMethod.GET)
	public List<Author> getAuthorsByBookId(@RequestParam(name = "book") int bookId) {
		return bookService.getAuthorsByBookId(bookId);
	}
	@RequestMapping(value = "/genre/book", method = RequestMethod.GET)
	public Genre getGenreByBookId(@RequestParam(name = "book") int bookId){
		return bookService.getGenreByBookId(bookId);
	}


}
