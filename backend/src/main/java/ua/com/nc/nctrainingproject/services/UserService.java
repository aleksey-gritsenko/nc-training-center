package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserPostgreDAO;

@Service
public class UserService {
	private final UserPostgreDAO userPostgreDAO;

	@Autowired
	UserService(UserPostgreDAO userPostgreDAO) {
		this.userPostgreDAO = userPostgreDAO;
	}

	public User updateByName(String userName, User user) { // There is no check for the same user
		User currUser = userPostgreDAO.getUserByUserName(userName);
		boolean isSameEmail = currUser.getEmail().equals(user.getEmail());
		boolean isSameName = currUser.getUserName().equals(user.getUserName());
		boolean isUserByEmail = userPostgreDAO.getUserByEmail(user.getEmail()) != null;
		boolean isUserByName = userPostgreDAO.getUserByUserName(user.getUserName()) != null;

		if (isSameName) {
			if (!isUserByEmail || isSameEmail) {

				userPostgreDAO.updateUserByName(userName, user);
				return userPostgreDAO.getUserByUserName(user.getUserName());
			}

		} else if (!isUserByName && !isUserByEmail || isSameEmail) {
			userPostgreDAO.updateUserByName(userName, user);
			return userPostgreDAO.getUserByUserName(user.getUserName());
		}
		return null;
	}

	public User getByName(String userName) {
		return userPostgreDAO.getUserByUserName(userName);
	}
}
