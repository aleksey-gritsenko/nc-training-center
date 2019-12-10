package ua.com.nc.nctrainingproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	// In this way Spring serves Angular contents
	@RequestMapping("")
	public String gui() {
		return "forward:/index.html";
	}

}
