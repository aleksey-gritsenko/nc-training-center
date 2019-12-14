package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.CodePostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserPostgreDAO;

import java.util.List;

@Service
public class UserService {

	private final UserPostgreDAO userPostgreDAO;

	private final CodePostgreDAO codePostgreDAO;

	@Autowired
	UserService(UserPostgreDAO userPostgreDAO, CodePostgreDAO codePostgreDAO) {
		this.userPostgreDAO = userPostgreDAO;
		this.codePostgreDAO = codePostgreDAO;
	}

	public User updateByName(User user) {
		User currUser = userPostgreDAO.getUserByUserName(user.getUserName());
		boolean isSameEmail = currUser.getEmail().equals(user.getEmail());

		if (!isSameEmail || !currUser.getUserPassword().equals(user.getUserPassword())) {
			if (!isSameEmail && userPostgreDAO.getUserByEmail(user.getEmail()) != null) {
				return null;
			}
			userPostgreDAO.updateUserByName(user.getUserName(), user);
			return userPostgreDAO.getUserByUserName(user.getUserName());
		}
		return currUser;
	}

	public User getById(int id) {
		User user = userPostgreDAO.getUserById(id);

		return user != null && user.isActivated() ? user : null;
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
