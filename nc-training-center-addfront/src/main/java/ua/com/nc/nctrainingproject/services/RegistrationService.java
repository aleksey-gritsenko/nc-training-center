package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserPostgreDAO;

@Service
public class RegistrationService {

    @Autowired
    UserPostgreDAO userPostgreDAO;

    public boolean reg(String login, String password, String email){
        User user = userPostgreDAO.getUserByUserName(login);
        if (user != null){
            return false;
        } else {
            user = new User(login, password, email);
            userPostgreDAO.createUser(user);
        }
        return true;
    }
}
