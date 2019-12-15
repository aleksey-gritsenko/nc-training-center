package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.SettingsListPostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserPostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserSettingsPostgreDAO;

@Service
@EnableScheduling
public class AuthorizationService {
    private final UserPostgreDAO userPostgreDAO;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserSettingsPostgreDAO userSettingsPostgreDAO;
    private final SettingsListPostgreDAO settingsListPostgreDAO;

    @Autowired
    public AuthorizationService(UserPostgreDAO userPostgreDAO, BCryptPasswordEncoder bCryptPasswordEncoder, UserSettingsPostgreDAO userSettingsPostgreDAO, SettingsListPostgreDAO settingsListPostgreDAO) {
        this.userPostgreDAO = userPostgreDAO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userSettingsPostgreDAO = userSettingsPostgreDAO;
        this.settingsListPostgreDAO = settingsListPostgreDAO;
    }

    public User auth(String login, String password) {
        if (!login.isEmpty() && !password.isEmpty()) {
            User user = userPostgreDAO.getUserByUserName(login);

            if (user != null && user.isActivated()) {
                if (bCryptPasswordEncoder.matches(password, user.getUserPassword())) {
                    user.setUserPassword("");
                    return user;
                }
            }
        }
        return null;
    }


    public User register(String login, String password, String email) {
        System.out.println("In the register");
        if (!login.isEmpty() && !password.isEmpty() && !email.isEmpty()) {
            if (userPostgreDAO.getUserByUserName(login) == null && userPostgreDAO.getUserByEmail(email) == null) {
                User user = new User(login, bCryptPasswordEncoder.encode(password), email);
                user.setUserRole("user");

                userPostgreDAO.createUser(user);

                createDefaultUserSettings(userPostgreDAO.getUserByUserName(login).getId());

                return userPostgreDAO.getUserByUserName(login);
            }
        }
        return null;
    }

    public void createDefaultUserSettings(int userId) {
        settingsListPostgreDAO.createSettings(userId);
        userSettingsPostgreDAO.createSettings(userId, settingsListPostgreDAO.getSettingsListIdByUserId(userId));
    }

    @Scheduled(fixedRate = 7200000) // 2 hours in milliseconds
    public void checkUserActivation() {
        userPostgreDAO.checkAccountActivation(24);
    }
}
