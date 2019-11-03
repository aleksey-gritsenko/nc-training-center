package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.Admin;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.AdministratorPostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserPostgreDAO;

@Service
public class AuthorizationService {

    @Autowired
    UserPostgreDAO userPostgreDAO;

    @Autowired
    AdministratorPostgreDAO administratorPostgreDAO;

    public boolean auth(String login, String password) {
        User user = userPostgreDAO.getUserByUserName(login);
        return user != null && user.getUserPassword().equals(password);
    }
}
