package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.CodePostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserPostgreDAO;

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

        return user.isActivated() ? user : null;
    }

    public User createAdmin(User admin) {
        if (userPostgreDAO.getUserByUserName(admin.getUserName()) == null
                && userPostgreDAO.getUserByEmail(admin.getEmail()) == null) {

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
}
