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
        return userPostgreDAO.getUserById(id);
    }

    public User createAdmin(User admin) {
        if (userPostgreDAO.getUserByUserName(admin.getUserName()) == null
                && userPostgreDAO.getUserByEmail(admin.getEmail()) == null) {

            userPostgreDAO.createAdmin(admin);
            return userPostgreDAO.getUserByUserName(admin.getUserName());
        }
        return null;
    }
}
