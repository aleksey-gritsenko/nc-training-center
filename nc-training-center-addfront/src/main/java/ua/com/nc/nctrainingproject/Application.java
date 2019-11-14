package ua.com.nc.nctrainingproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserPostgreDAO;


@SpringBootApplication
public class Application {
    @Autowired
   static UserPostgreDAO userPostgreDAO;


	public static void main(String[] args) {
			SpringApplication.run(Application.class, args);
	}
}
