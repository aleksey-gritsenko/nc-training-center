//package ua.com.nc.nctrainingproject.configuration.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import ua.com.nc.nctrainingproject.models.User;
//import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserPostgreDAO;
//
//@Service
//public class MyUserDetailsServise implements UserDetailsService {
//    private final UserPostgreDAO userPostgreDAO;
//
//    @Autowired
//    public MyUserDetailsServise(UserPostgreDAO userPostgreDAO) {
//        this.userPostgreDAO = userPostgreDAO;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        User user = userPostgreDAO.getUserByUserName(s);
//        return new PrincipalUser(user);
//    }
//}
