package ua.com.nc.nctrainingproject.services;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.Admin;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.persistance.dao.AdministratorDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.AdministratorPostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserPostgreDAO;

import java.util.Map;

@Service
public class AuthorizationService {
    private final UserPostgreDAO userPostgreDAO;
    private final AdministratorDAO administratorDAO;

    @Autowired
    public AuthorizationService(UserPostgreDAO userPostgreDAO, AdministratorDAO administratorDAO) {
        this.userPostgreDAO = userPostgreDAO;
        this.administratorDAO = administratorDAO;
    }

    public Map<String, Object> auth(String login, String password) {
        User user = userPostgreDAO.getUserByUserName(login);
        Admin admin = administratorDAO.getAdministratorByName(login);
        JSONObject responseJSON;

        if (user != null) {
            if (user.getUserPassword().equals(password)){
                responseJSON = new JSONObject();
                responseJSON.put("isAdmin", false);
                responseJSON.put("user", user);

                return responseJSON.toMap();
            }
        } else if (admin != null) {
            if (admin.getAdminPassword().equals(password)) {
                responseJSON = new JSONObject();
                responseJSON.put("isAdmin", true);
                responseJSON.put("user", admin);

                return responseJSON.toMap();
            }
        }
        return null;
    }

    public User register(String login, String password, String email){
        User user = userPostgreDAO.getUserByUserName(login);

        if (user == null) {
            user = new User(login, password, email);
            userPostgreDAO.createUser(user);

            return user;
        }
        return null;
    }
}
