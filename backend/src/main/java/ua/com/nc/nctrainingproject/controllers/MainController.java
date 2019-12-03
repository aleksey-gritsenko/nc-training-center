package ua.com.nc.nctrainingproject.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String welcome() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        return "login";
    }

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

}
