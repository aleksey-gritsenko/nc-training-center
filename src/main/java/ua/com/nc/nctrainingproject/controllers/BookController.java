package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.Author;
import ua.com.nc.nctrainingproject.models.Book;
import ua.com.nc.nctrainingproject.services.BookService;

import javax.sound.midi.SysexMessage;
import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class BookController {


	private final BookService bookService;

	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public Book addBook(@RequestParam(name = "header") String header,
						@RequestParam(name = "overview") String overview,
						@RequestParam(name = "photo") String photo,
						@RequestParam(name = "file") int fileId,
						@RequestParam(name = "status") String status){


		return bookService.createBook(header, overview, status, photo, fileId);
	}

	@RequestMapping(value = "/book/update", method = RequestMethod.POST)
	public Book updateBook(@RequestParam(name = "bookId") int bookId,
						   @RequestParam(name = "header") String header,
						   @RequestParam(name = "overview") String overview,
						   @RequestParam(name = "photo") String photo,
						   @RequestParam(name = "file") int fileId,
						   @RequestParam(name = "status") String status) {

		return bookService.updateBook(bookId, header, overview, status, photo, fileId);
	}
  @RequestMapping(value = "/book/id", method = RequestMethod.GET)
  @ResponseBody
  public Book getBookById(@RequestParam(name = "id") int bookId){
	  return bookService.getBookById(bookId);
  }


	@RequestMapping(value = "/book/all", method = RequestMethod.GET)
	public List<Book> getAllBooks(){
		return  bookService.getAllBooks();
	}

	/*
  @RequestMapping(value = "/book/filter", method = RequestMethod.GET)

  public List<Book> filterBook
      ( @RequestParam(name = "header") String header
       // @RequestParam(name = "date") ArrayList<Date> dates,
        // @RequestParam(name = "genre") ArrayList<String> genre,
       // @RequestParam(name = "author") ArrayList<String> author
     ){
	  ArrayList<String> genres = new ArrayList<>();

	 genres.add("fiction");
	 //genres.add("drama");
	ArrayList<String> authors = new ArrayList<>();
	  authors.add("none");
	  return bookService.filterBooks(header+"%",genres,authors);
  }
*/
}
