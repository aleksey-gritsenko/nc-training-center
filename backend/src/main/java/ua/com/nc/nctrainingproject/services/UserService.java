package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.CodePostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserPostgreDAO;

import java.util.List;

@Service
public class UserService {
	public static final String EMAIL_PATTERN = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
	public static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";

	private final UserPostgreDAO userPostgreDAO;
	private final CodePostgreDAO codePostgreDAO;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;


	@Autowired
	UserService(UserPostgreDAO userPostgreDAO, CodePostgreDAO codePostgreDAO, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userPostgreDAO = userPostgreDAO;
		this.codePostgreDAO = codePostgreDAO;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public User updateByName(User user) {
		User currUser = userPostgreDAO.getUserByUserName(user.getUserName());
		String email = user.getEmail();
		String password = user.getUserPassword();

		if (!email.isEmpty() && email.matches(EMAIL_PATTERN) && (userPostgreDAO.getUserByEmail(email) == null || currUser.getEmail().equals(user.getEmail()))) {
			if (password.isEmpty()) {
				user.setUserPassword(currUser.getUserPassword());
				userPostgreDAO.updateUserByName(user.getUserName(), user);
			} else {
				if (password.matches(PASSWORD_PATTERN)) {
					user.setUserPassword(bCryptPasswordEncoder.encode(password));
					userPostgreDAO.updateUserByName(user.getUserName(), user);
				} else {
					return null;
				}
			}
			currUser = userPostgreDAO.getUserByUserName(user.getUserName());
			currUser.setUserPassword("");
			return currUser;
		}
		return null;
	}

	public User getById(int id) {
		User user = userPostgreDAO.getUserById(id);
		if (user != null && user.isActivated()) {
			user.setUserPassword("");

			return user;
		}
		return null;
	}

	public User createAdmin(User admin) {
		if (userPostgreDAO.getUserByUserName(admin.getUserName()) == null
				&& userPostgreDAO.getUserByEmail(admin.getEmail()) == null) {

			admin.setVerified(true);
			userPostgreDAO.createAdmin(admin);
			return userPostgreDAO.getUserByUserName(admin.getUserName());
		}
		return null;
	}

	public User activateAccount(String email, String code) {

		if (codePostgreDAO.getCodeBy(code) != null) {
			userPostgreDAO.activateAccount(email);
			return userPostgreDAO.getUserByEmail(email);
		}
		return null;
	}

	public boolean deactivateAccount(int id) {
		if (userPostgreDAO.getUserById(id) != null) {
			userPostgreDAO.deactivateAccount(id);

			return true;
		}
		return false;
	}

	public List<User> searchUsersByUsername(String search) {
		search = search.toLowerCase();
		return userPostgreDAO.searchUsersByUsername(search);
	}

	public List<User> getAllAdmins() {
		return userPostgreDAO.getAllAdmins();
	}

	public List<User> getAllModerators() {
		return userPostgreDAO.getAllModerators();
	}

	public List<User> getActivatedModerators() {
		return userPostgreDAO.getActivatedModerators();
	}

	public List<User> getActivatedAdmins() {
		return userPostgreDAO.getActivatedAdmins();
	}

	public List<User> getAllUsers() {return userPostgreDAO.getAllUsers();}
}
