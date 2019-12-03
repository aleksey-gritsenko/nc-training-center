package ua.com.nc.nctrainingproject.configuration.config;

import ua.com.nc.nctrainingproject.models.User;

import java.util.List;

public interface UserService {
    User save(User user);
    List<User> findAll();
    User findOne(long id);
    void delete(long id);
}
