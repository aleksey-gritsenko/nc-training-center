package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.Book;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.FilterCriterionQuery;
import ua.com.nc.nctrainingproject.services.BookService;

import java.util.List;
import java.util.Optional;

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
}
