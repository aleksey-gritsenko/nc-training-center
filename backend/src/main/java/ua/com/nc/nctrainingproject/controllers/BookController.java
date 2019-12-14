package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.nc.nctrainingproject.models.Book;
import ua.com.nc.nctrainingproject.models.BookFile;
import ua.com.nc.nctrainingproject.models.BookImage;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.FilterCriterionQuery;
import ua.com.nc.nctrainingproject.services.BookFileManagementService;
import ua.com.nc.nctrainingproject.services.BookService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/book")
public class BookController {
	private final BookService bookService;
	private final BookFileManagementService bookFileManagementService;

	@Autowired
	public BookController(BookService bookService, BookFileManagementService bookFileManagementService) {
		this.bookService = bookService;
		this.bookFileManagementService = bookFileManagementService;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		Book response = bookService.createBook(book);
		return Optional.ofNullable(response).map(ResponseEntity::ok)
				.orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<Book> updateBook(@RequestBody Book book) {
		Book response = bookService.updateBook(book);
		return Optional.ofNullable(response).map(ResponseEntity::ok)
				.orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/id", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getBookById(@RequestParam(name = "id") String bookId) {
		return ResponseEntity.ok(bookService.getBookById(Integer.parseInt(bookId)));
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}

	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<Book>> filterBook(@RequestBody FilterCriterionQuery filterCriterionQuery
	) {
	    if(!(filterCriterionQuery.getHeader().trim().isEmpty() &&
                filterCriterionQuery.getAuthor().size()==0 &&
                filterCriterionQuery.getGenre().size() ==0)) {
            List<Book> books = bookService.filterBooks(filterCriterionQuery);
            return ResponseEntity.ok(books);
        }
	    return  ResponseEntity.ok(new ArrayList<Book>());
	}

	@RequestMapping(value = "/filterUser", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<Book>> filterUserBook(@RequestBody FilterCriterionQuery filterCriterionQuery) {
		if(!(filterCriterionQuery.getHeader().trim().isEmpty() &&
				filterCriterionQuery.getAuthor().size() == 0 &&
				filterCriterionQuery.getGenre().size() == 0)) {
			List<Book> books = bookService.filterUserBooks(filterCriterionQuery);
			return ResponseEntity.ok(books);
		}
		return ResponseEntity.ok(new ArrayList<>());
	}

	@RequestMapping(value = "/genres", method = RequestMethod.GET)
	public ResponseEntity<?> getAllGenres() {
		return ResponseEntity.ok(bookService.getAllGenres());
	}

	@RequestMapping(value = "/authors", method = RequestMethod.GET)
	public ResponseEntity<?> getAllAuthors() {
		return ResponseEntity.ok(bookService.getAllAuthors());
	}

	@RequestMapping(value = "/genresName", method = RequestMethod.GET)
	public ResponseEntity<?> getAllGenresName() {
		return ResponseEntity.ok(bookService.getAllGenresName());
	}

	@RequestMapping(value = "/authors/book", method = RequestMethod.GET)
	public ResponseEntity<?> getAuthorsByBookId(@RequestParam(name = "book") int bookId) {
		return ResponseEntity.ok(bookService.getAuthorsByBookId(bookId));
	}

	@RequestMapping(value = "/genre/book", method = RequestMethod.GET)
	public ResponseEntity<?> getGenreByBookId(@RequestParam(name = "book") int bookId) {
		return ResponseEntity.ok(bookService.getGenreByBookId(bookId));
	}

	@RequestMapping(value = "/suggestion", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> makeSuggestion(@RequestParam(name = "user") int userId) {
		return ResponseEntity.ok(bookService.makeSuggestion(userId));
	}

	@RequestMapping(value = "/rate", method = RequestMethod.GET)
	public ResponseEntity<?> getMostRatedBook() {
		return ResponseEntity.ok(bookService.getMostRatedBooks());
	}

	// ========== FILE MANAGEMENT ==========

	@RequestMapping(value = "/addFile", method = RequestMethod.POST)
	public ResponseEntity<BookFile> addBookFile(@RequestParam(name = "bookId") int book,
										 @RequestParam(name = "file") MultipartFile file) throws IOException {
		BookFile bookFile = new BookFile(book, file.getBytes());
		BookFile response = bookFileManagementService.addFile(bookFile);
		return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/addImage", method = RequestMethod.POST)
	public ResponseEntity<BookImage> addBookImage(@RequestParam(name = "bookId") int book,
										  @RequestParam(name = "img") MultipartFile file) throws IOException {
		BookImage bookImage = new BookImage(book, file.getBytes());
		BookImage response = bookFileManagementService.addImage(bookImage);
		return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}


	@RequestMapping(produces = MediaType.APPLICATION_PDF_VALUE, value = "/bookFile", method = RequestMethod.POST)
	public ResponseEntity<?> getBookFile(@RequestBody Book book) {
		ByteArrayInputStream stream = new ByteArrayInputStream(bookFileManagementService.getBookFile(book).getFile());
		System.out.println(stream);
		return ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(stream));
	}

	@RequestMapping(produces = MediaType.IMAGE_PNG_VALUE, value = "/bookImage")
	@ResponseBody
	public ResponseEntity<byte[]> getBookImage(@RequestBody Book book) {
		BookImage bookImage = bookFileManagementService.getBookImage(book);
		return Optional.ofNullable(bookImage.getImage()).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/updateFile", method = RequestMethod.POST)
	public ResponseEntity<BookFile> updateBookFile(@RequestBody Book book,
												   @RequestParam(name = "file") MultipartFile file) throws IOException {
		BookFile bookFile = bookFileManagementService.getBookFile(book);
		bookFile.setFile(file.getBytes());

		BookFile response = bookFileManagementService.updateFile(bookFile);
		return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/updateImage", method = RequestMethod.POST)
	public ResponseEntity<BookImage> updateBookImage(@RequestBody Book book,
													 @RequestParam(name = "img") MultipartFile file) throws IOException {
		BookImage bookImage = bookFileManagementService.getBookImage(book);
		bookImage.setImage(file.getBytes());

		BookImage response = bookFileManagementService.updateImage(bookImage);
		return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/deleteImage", method = RequestMethod.POST)
	public ResponseEntity<BookImage> deleteBookImage(@RequestBody Book book) {

		BookImage bookImage = bookFileManagementService.getBookImage(book);
		BookImage response = bookFileManagementService.deleteImage(bookImage);

		return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	@RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
	public ResponseEntity<BookFile> deleteBookFile(@RequestBody Book book) {

		BookFile bookFile = bookFileManagementService.getBookFile(book);
		BookFile response = bookFileManagementService.deleteFile(bookFile);

		return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}
}
