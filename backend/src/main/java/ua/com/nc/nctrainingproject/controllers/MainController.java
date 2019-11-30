package ua.com.nc.nctrainingproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

   /* @GetMapping("/")
    public String welcome() {
        return "login";
    }*/

    @GetMapping("/registration")
    public String reg(){
        return "registration";
    }

    @GetMapping("/book")
    public String addBook(){
        return "book";
    }

    @GetMapping("/book/update")
    public String updateBook(){
        return "updateBook";
    }

    @GetMapping("/review")
    public String addReview() {return "review";}

    @GetMapping("/userBook")
    public String addUserBook() {return "userBook";}

	@GetMapping("/notification")
	public String createNotification() {
		return "notification";
	}

	// In this way Spring serves Angular contents
    @RequestMapping({ "", "/login", "/products/**" })
    public String gui() {
        return "forward:/index.html";
    }

}
