package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.Author;
import ua.com.nc.nctrainingproject.models.Book;
import ua.com.nc.nctrainingproject.services.BookService;

import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class BookController {


//	private final BookService bookService;
//
//	@Autowired
//	public BookController(BookService bookService) {
//		this.bookService = bookService;
//	}
//
//	@RequestMapping(value = "/book", method = RequestMethod.POST)
//	public Book addBook(@RequestParam(name = "title") String title,
//						@RequestParam(name = "header") String header,
//						@RequestParam(name = "author") String author,
//						@RequestParam(name = "overview") String overview,
//						@RequestParam(name = "photo") int photoId,
//						@RequestParam(name = "file") int fileId,
//						@RequestParam(name = "status") String status) {
//
//		return bookService.createBook(title, header, author, overview, status, photoId, fileId);
//	}
//
//	@RequestMapping(value = "/book/update", method = RequestMethod.POST)
//	public Book updateBook(@RequestParam(name = "bookId") int bookId,
//						   @RequestParam(name = "title") String title,
//						   @RequestParam(name = "header") String header,
//						   @RequestParam(name = "author") String author,
//						   @RequestParam(name = "overview") String overview,
//						   @RequestParam(name = "photo") int photoId,
//						   @RequestParam(name = "file") int fileId,
//						   @RequestParam(name = "status") String status) {
//
//		return bookService.updateBook(bookId, title, header, author, overview, status, photoId, fileId);
//	}
//<<<<<<< HEAD
//=======
////=======
//	private final BookService bookService;
//
//	@Autowired
//	public BookController(BookService bookService) {
//		this.bookService = bookService;
//	}
//>>>>>>> 5e8f7985b1827f2253e072a1798cc1dc3eff8321
//
//	private final BookService bookService;
//
//	@Autowired
//	public BookController(BookService bookService) {
//		this.bookService = bookService;
//	}
//
//	@RequestMapping(value = "/book", method = RequestMethod.POST)
//	public Book addBook(@RequestParam(name = "title") String title,
//						@RequestParam(name = "header") String header,
//						@RequestParam(name = "author") String author,
//						@RequestParam(name = "overview") String overview,
//						@RequestParam(name = "photo") int photoId,
//						@RequestParam(name = "file") int fileId,
//						@RequestParam(name = "status") String status,
//						@RequestParam(name = "genre") String genre) {
//
//
//		return bookService.createBook(title, header, author, overview, status, photoId, fileId, genre);
//	}
//
//	@RequestMapping(value = "/book/update", method = RequestMethod.POST)
//	public Book updateBook(@RequestParam(name = "bookId") int bookId,
//						   @RequestParam(name = "title") String title,
//						   @RequestParam(name = "header") String header,
//						   @RequestParam(name = "author") String author,
//						   @RequestParam(name = "overview") String overview,
//						   @RequestParam(name = "photo") int photoId,
//						   @RequestParam(name = "file") int fileId,
//						   @RequestParam(name = "status") String status,
//						   @RequestParam(name = "genre") String genre) {
//
//		return bookService.updateBook(bookId, title, header, author, overview, status, photoId, fileId, genre);
//	}
//  @RequestMapping(value = "/book/title", method = RequestMethod.GET)
//  @ResponseBody
//  public List<Book> findBookByTitle(@RequestParam(name = "title") String title){
//	  return bookService.findBookByTitle(title);
//
//  }
//
//
//	@RequestMapping(value = "/book/all", method = RequestMethod.GET)
//	public List<Book> getAllBooks(){
//		return  bookService.getAllBooks();
//	}
//<<<<<<< HEAD
//=======
//  @RequestMapping(value = "/book/filter", method = RequestMethod.GET)
//
//  public List<Book> filterBook
//      ( @RequestParam(name = "header") String header
//       // @RequestParam(name = "date") ArrayList<Date> dates,
//    //   @RequestParam(name = "genre") ArrayList<String> genre,
//       // @RequestParam(name = "author") ArrayList<String> author
//     ){
//	  ArrayList<String> genres = new ArrayList<>();
//
//	 genres.add("fiction");
//	 //genres.add("drama");
//	ArrayList<String> authors = new ArrayList<>();
//	  authors.add("none");
//	  return bookService.filterBooks(header+"%",genres,authors);
//  }
//>>>>>>> 6370a182546e95818761b382343272ba4d99af66
//>>>>>>> 5e8f7985b1827f2253e072a1798cc1dc3eff8321
//  @RequestMapping(value = "/book/filter", method = RequestMethod.GET)
//
//  public List<Book> filterBook
//      ( @RequestParam(name = "header") String header,
//        @RequestParam(name = "date") ArrayList<Date> dates,
//       @RequestParam(name = "genre") ArrayList<String> genre,
//        @RequestParam(name = "author") ArrayList<String> author
//     ){
//	  ArrayList<String> genres = new ArrayList<>();
//
//	 //genres.add("fiction");
//	 //genres.add("drama");
//	//  ArrayList<String> authors = new ArrayList<>();
//	 // authors.add("none");
//	  return bookService.filterBooks(header+"%",genres,author);
//  }
//<<<<<<< HEAD



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

//=======
//>>>>>>> 5e8f7985b1827f2253e072a1798cc1dc3eff8321
}
