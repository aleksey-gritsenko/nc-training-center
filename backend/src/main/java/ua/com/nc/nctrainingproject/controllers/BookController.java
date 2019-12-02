package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.Book;
import ua.com.nc.nctrainingproject.models.BookFile;
import ua.com.nc.nctrainingproject.models.BookImage;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.FilterCriterionQuery;
import ua.com.nc.nctrainingproject.services.BookFileManagementService;
import ua.com.nc.nctrainingproject.services.BookService;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    // FILE MANAGEMENT

    @RequestMapping(value = "/addFile", method = RequestMethod.POST)
    public ResponseEntity<?> addBookFile(@RequestBody Book book, @RequestBody File file) throws FileNotFoundException {
        BookFile bookFile = new BookFile(book.getId(), new FileInputStream(file));
        BookFile response = bookFileManagementService.addFile(bookFile);
        return response != null ? ResponseEntity.ok(response) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/addImage", method = RequestMethod.POST)
    public ResponseEntity<?> addBookImage(@RequestBody Book book, @RequestBody File file) throws FileNotFoundException {
        BookImage bookImage = new BookImage(book.getId(), new FileInputStream(file));
        BookImage response = bookFileManagementService.addImage(bookImage);
        return response != null ? ResponseEntity.ok(response) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

//    @RequestMapping(value = "/addImageFile", method = RequestMethod.POST)
//    public ResponseEntity<?> addBookImageFile(@RequestBody Book book,
//                                              @RequestBody File image,
//                                              @RequestBody File file)
//            throws FileNotFoundException {
//        BookImage bookImage = new BookImage(book.getId(), new FileInputStream(image));
//        BookFile bookFile = new BookFile(book.getId(), new FileInputStream(file));
//
//        BookImage imageResponse = bookFileManagementService.addImage(bookImage);
//        BookFile fileResponse = bookFileManagementService.addFile(bookFile);
//
//        if (imageResponse != null && fileResponse != null)
//        {
//
//        }
//    }

    @RequestMapping(value = "/updateFile", method = RequestMethod.POST)
    public ResponseEntity<?> updateBookFile(@RequestBody Book book, @RequestBody File file) throws FileNotFoundException{
        BookFile bookFile = new BookFile(book.getId(), new FileInputStream(file));
        BookFile response = bookFileManagementService.updateFile(bookFile);
        return response != null ? ResponseEntity.ok(response) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/updateImage", method = RequestMethod.POST)
    public ResponseEntity<?> updateBookImage(@RequestBody Book book, @RequestBody File file) throws FileNotFoundException{
        BookImage bookImage = new BookImage(book.getId(), new FileInputStream(file));
        BookImage response = bookFileManagementService.updateImage(bookImage);
        return response != null ? ResponseEntity.ok(response) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/deleteImage", method = RequestMethod.POST)
    public ResponseEntity<?> deleteBookImage(@RequestBody Book book, @RequestBody File file) throws FileNotFoundException{
        BookImage bookImage = new BookImage(book.getId(), new FileInputStream(file));
        BookImage response = bookFileManagementService.deleteImage(bookImage);
        return response != null ? ResponseEntity.ok(response) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
    public ResponseEntity<?> deleteBookFile(@RequestBody Book book, @RequestBody File file) throws FileNotFoundException{
        BookFile bookFile = new BookFile(book.getId(), new FileInputStream(file));
        BookFile response = bookFileManagementService.deleteFile(bookFile);
        return response != null ? ResponseEntity.ok(response) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
