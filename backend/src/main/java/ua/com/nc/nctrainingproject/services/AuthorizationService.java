package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserPostgreDAO;

@Service
@EnableScheduling
public class AuthorizationService {
	private final UserPostgreDAO userPostgreDAO;

	@Autowired
	public AuthorizationService(UserPostgreDAO userPostgreDAO) {
		this.userPostgreDAO = userPostgreDAO;
	}

	public User auth(String login, String password) {
		if (!login.isEmpty() && !password.isEmpty()) {
			User user = userPostgreDAO.getUserByUserName(login);

			if (user != null && user.isActivated()) {
				if (user.getUserPassword().equals(password)) {
					return user;
				}
			}
		}
		return null;
	}

	public User register(String login, String password, String email) {
		if (!login.isEmpty() && !password.isEmpty() && !email.isEmpty()) {
			if (userPostgreDAO.getUserByUserName(login) == null && userPostgreDAO.getUserByEmail(email) == null) {
				User user = new User(login, password, email);
				user.setUserRole("user");

				userPostgreDAO.createUser(user);

				return userPostgreDAO.getUserByUserName(login);
			}
		}
		return null;
	}

	@Scheduled(fixedRate = 7200000) // 2 hours in milliseconds
	public void checkUserActivation() {
		userPostgreDAO.checkAccountActivation(24);
	}
}
