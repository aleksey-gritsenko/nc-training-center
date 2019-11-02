package ua.com.nc.nctrainingproject.persistance.dao;

import ua.com.nc.nctrainingproject.model.User;

import java.util.List;

public interface UserDAO {
    User getUserByUserName(String userName);

    void createUser(User user);

    List<User> getAllUsers();
}
