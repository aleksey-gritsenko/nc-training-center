package ua.com.nc.nctrainingproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String welcome() {
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

}
