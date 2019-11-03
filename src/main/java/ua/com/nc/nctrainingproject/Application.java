package ua.com.nc.nctrainingproject;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
=======
import com.sun.mail.util.MailSSLSocketFactory;
>>>>>>> 65983835960fd901cb0ed56b2761d1a422b41dae
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserPostgreDAO;

import java.util.Properties;

@SpringBootApplication
public class Application {
    @Autowired
   static UserPostgreDAO userPostgreDAO;


<<<<<<< HEAD
        SpringApplication.run(Application.class, args);
       // Administrator administrator = new AdministratorPostgreDAO().getAdministratorByName("MainGod");
       // System.out.println(administrator);

       // System.out.println(new UserPostgreDAO().getUserByUserName("Maria"));
     //   System.out.println(userPostgreDAO.getUserByUserName("Maria"));
=======
	public static void main(String[] args) {

	/*	try {
			MailSSLSocketFactory socketFactory = new MailSSLSocketFactory();
			socketFactory.setTrustAllHosts(true);
			Properties prop = new Properties();
			prop.put("mail.smtp.ssl.socketFactory", socketFactory);
			prop.put("mail.smtp.ssl.trust", "*");
*/
			SpringApplication.run(Application.class, args);
			/*
		} catch (Exception e) {
			e.printStackTrace();
		}
>>>>>>> 65983835960fd901cb0ed56b2761d1a422b41dae

	/
			 */
	}
}
