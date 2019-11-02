package ua.com.nc.nctrainingproject;

import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserPostgreDAO;

import java.util.Properties;

@SpringBootApplication
public class Application {


	public static void main(String[] args) {

		try {
			MailSSLSocketFactory socketFactory = new MailSSLSocketFactory();
			socketFactory.setTrustAllHosts(true);
			Properties prop = new Properties();
			prop.put("mail.smtp.ssl.socketFactory", socketFactory);
			prop.put("mail.smtp.ssl.trust", "*");

			SpringApplication.run(Application.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
