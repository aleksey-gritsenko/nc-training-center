package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
	public Book addBook(@RequestParam(name = "title") String title,
						@RequestParam(name = "header") String header,
						@RequestParam(name = "author") String author,
						@RequestParam(name = "overview") String overview,
						@RequestParam(name = "photo") int photoId,
						@RequestParam(name = "file") int fileId,
						@RequestParam(name = "status") String status,
						@RequestParam(name = "genre") String genre) {


		return bookService.createBook(title, header, author, overview, status, photoId, fileId, genre);
	}

	@RequestMapping(value = "/book/update", method = RequestMethod.POST)
	public Book updateBook(@RequestParam(name = "bookId") int bookId,
						   @RequestParam(name = "title") String title,
						   @RequestParam(name = "header") String header,
						   @RequestParam(name = "author") String author,
						   @RequestParam(name = "overview") String overview,
						   @RequestParam(name = "photo") int photoId,
						   @RequestParam(name = "file") int fileId,
						   @RequestParam(name = "status") String status,
						   @RequestParam(name = "genre") String genre) {

		return bookService.updateBook(bookId, title, header, author, overview, status, photoId, fileId, genre);
	}
  @RequestMapping(value = "/book/title", method = RequestMethod.GET)
  @ResponseBody
  public List<Book> findBookByTitle(@RequestParam(name = "title") String title){
	  return bookService.findBookByTitle(title);

  }


	@RequestMapping(value = "/book/all", method = RequestMethod.GET)
	public List<Book> getAllBooks(){
		return  bookService.getAllBooks();
	}
  @RequestMapping(value = "/book/filter", method = RequestMethod.GET)

  public List<Book> filterBook
      ( @RequestParam(name = "header") String header,
       @RequestParam(name = "genre") ArrayList<String> genres,
        @RequestParam(name = "author") ArrayList<String> authors
     ){

	  return bookService.filterBooks(header+"%",genres,authors);
  }
}
