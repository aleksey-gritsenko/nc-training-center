//package ua.com.nc.nctrainingproject.configuration.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class AuthentificateService {
//    private final MyUserDetailsServise myUserDetailsServise;
//
//    @Autowired
//    public AuthentificateService(MyUserDetailsServise myUserDetailsServise) {
//        this.myUserDetailsServise = myUserDetailsServise;
//    }
//
//    public void authentificate(String password, String login) {
//        UserDetails userDetails = myUserDetailsServise.loadUserByUsername(login);
//        if (userDetails.getPassword().equals(password) && !SecurityContextHolder.getContext().getAuthentication().getName().equals(login)) {
//
//            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password);
//            SecurityContextHolder.getContext().setAuthentication(token);
//        }
//    }
//}
