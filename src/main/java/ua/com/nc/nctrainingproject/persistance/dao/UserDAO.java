package ua.com.nc.nctrainingproject.persistance.dao;

import ua.com.nc.nctrainingproject.models.User;

import java.util.List;

public interface UserDAO {
    User getUserByUserName(String userName);

    void createUser(User user);

    List<User> getAllUsers();

    void updateUserByName(String userName, User user);
}
