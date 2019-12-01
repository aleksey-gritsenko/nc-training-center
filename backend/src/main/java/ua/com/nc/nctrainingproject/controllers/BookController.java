package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.Book;
import ua.com.nc.nctrainingproject.services.BookService;

import java.util.ArrayList;
import java.util.Arrays;
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
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        Book response = bookService.createBook(book);

        return response != null ? ResponseEntity.ok(response) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateBook(@RequestBody Book book) {
        Book response = bookService.updateBook(book);
        return response != null ? ResponseEntity.ok(response) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
    public ResponseEntity<?> filterBook(@RequestParam(name="header") String header,
                                 @RequestParam(name="genre") ArrayList<String> genres,
                                 @RequestParam(name="author") ArrayList<String> authors
    ) {
        return ResponseEntity.ok(bookService.filterBooks(header + "%", genres, authors));
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
}
