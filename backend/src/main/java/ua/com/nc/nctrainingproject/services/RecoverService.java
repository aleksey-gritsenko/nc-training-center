package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserPostgreDAO;

@Service
public class RecoverService {
	private final UserPostgreDAO userPostgreDAO;

	@Autowired
	public RecoverService(UserPostgreDAO userPostgreDAO) {
		this.userPostgreDAO = userPostgreDAO;
	}

	public boolean check(String login, String email) {
		return userPostgreDAO.getUserByUserName(login) != null;
	}
}
