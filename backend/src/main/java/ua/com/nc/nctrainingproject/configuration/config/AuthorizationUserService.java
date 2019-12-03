package ua.com.nc.nctrainingproject.configuration.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserPostgreDAO;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@Service(value = "userAuthService")
public class AuthorizationUserService implements UserDetailsService, UserService {

    @Autowired
    UserPostgreDAO userPostgreDAO;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userPostgreDAO.getUserByUserName(s);
        if (user==null){
            throw new UsernameNotFoundException("Invalid username or password");
        }

        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPassword(), null);
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findOne(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
