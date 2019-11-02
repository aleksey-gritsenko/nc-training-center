package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserPostgreDAO;

@Service
public class AuthorizationService {

    @Autowired
    UserPostgreDAO userPostgreDAO;

    public boolean auth(String login, String password){
        User user = userPostgreDAO.getUserByUserName(login);
        return user != null && user.getUserPassword().equals(password);
    }

}
