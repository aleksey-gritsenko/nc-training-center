package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.Book;
import ua.com.nc.nctrainingproject.models.BookFile;
import ua.com.nc.nctrainingproject.models.BookImage;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.FilterCriterionQuery;
import ua.com.nc.nctrainingproject.services.BookFileManagementService;
import ua.com.nc.nctrainingproject.services.BookService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
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
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        Book response = bookService.createBook(book);
        return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateBook(@RequestBody Book book) {
        Book response = bookService.updateBook(book);

        return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/id", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getBookById(@RequestParam(name = "id") String bookId) {
        return ResponseEntity.ok(bookService.getBookById(Integer.parseInt(bookId)));
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> filterBook(@RequestBody FilterCriterionQuery filterCriterionQuery
                                   ) {
        List<Book> books = bookService.filterBooks(filterCriterionQuery.getHeader()
               , filterCriterionQuery.getGenre(), filterCriterionQuery.getAuthor());
        return ResponseEntity.ok(books);
    }

    @RequestMapping(value = "/genres", method = RequestMethod.GET)
    public ResponseEntity<?> getAllGenres() {
        return ResponseEntity.ok(bookService.getAllGenres());
    }

    @RequestMapping(value = "/authors", method = RequestMethod.GET)
    public ResponseEntity<?> getAllAuthors() {
        return ResponseEntity.ok(bookService.getAllAuthors());
    }

    @RequestMapping(value = "/authors/book", method = RequestMethod.GET)
    public ResponseEntity<?> getAuthorsByBookId(@RequestParam(name = "book") int bookId) {
        return ResponseEntity.ok(bookService.getAuthorsByBookId(bookId));
    }

    @RequestMapping(value = "/genre/book", method = RequestMethod.GET)
    public ResponseEntity<?> getGenreByBookId(@RequestParam(name = "book") int bookId) {
        return ResponseEntity.ok(bookService.getGenreByBookId(bookId));
    }

    @RequestMapping(value = "/suggestion", method = RequestMethod.GET)
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
    public ResponseEntity<BookFile> addBookFile(@RequestBody Book book, @RequestBody File file) throws IOException {

        byte[] encodedFile = Base64.getEncoder().encode(Files.readAllBytes(file.toPath()));

        BookFile bookFile = new BookFile(book.getId(), encodedFile);
        BookFile response = bookFileManagementService.addFile(bookFile);

        return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/addImage", method = RequestMethod.POST)
    public ResponseEntity<?> addBookImage(@RequestBody Book book, @RequestBody File file) throws IOException {

        byte[] encodedImage = Base64.getEncoder().encode(Files.readAllBytes(file.toPath()));

        BookImage bookImage = new BookImage(book.getId(), encodedImage);
        BookImage response = bookFileManagementService.addImage(bookImage);

        return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping(produces = MediaType.APPLICATION_PDF_VALUE, value = "/bookFile")
    public @ResponseBody byte[] getBookFile(@RequestBody Book book) {
        BookFile bookFile = bookFileManagementService.getBookFile((book));
        return bookFile.getFile();
    }

    @GetMapping(produces = MediaType.IMAGE_PNG_VALUE, value = "/bookImage")
    public @ResponseBody byte[] getBookImage(@RequestBody Book book) {
        BookImage bookImage = bookFileManagementService.getBookImage((book));
        return bookImage.getImage();
    }

    @RequestMapping(value = "/updateFile", method = RequestMethod.POST)
    public ResponseEntity<BookFile> updateBookFile(@RequestBody Book book, @RequestBody File file) throws IOException {
        byte[] encodedFile = Base64.getEncoder().encode(Files.readAllBytes(file.toPath()));

        BookFile bookFile = bookFileManagementService.getBookFile(book);
        bookFile.setFile(encodedFile);

        BookFile response = bookFileManagementService.updateFile(bookFile);
        return Optional.ofNullable(response).map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/updateImage", method = RequestMethod.POST)
    public ResponseEntity<BookImage> updateBookImage(@RequestBody Book book, @RequestBody File file) throws IOException{

        byte[] encodedImage = Base64.getEncoder().encode(Files.readAllBytes(file.toPath()));

        BookImage bookImage = bookFileManagementService.getBookImage(book);
        bookImage.setImage(encodedImage);

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
