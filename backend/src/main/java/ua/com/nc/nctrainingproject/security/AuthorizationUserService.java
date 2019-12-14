package ua.com.nc.nctrainingproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.AdminRightsPostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserPostgreDAO;

import java.util.List;

@Service(value = "userAuthService")
public class AuthorizationUserService implements UserDetailsService, UserService {

	@Autowired
	UserPostgreDAO userPostgreDAO;
	@Autowired
	AdminRightsPostgreDAO adminRightsPostgreDAO;


	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		User user = userPostgreDAO.getUserByUserName(s);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		System.out.println("AUTHORIZATION!!");

		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPassword(), getAuthority(user.getId()));
	}

	private List<SimpleGrantedAuthority> getAuthority(int adminId) {
		System.out.println("Trying to authorize");
		List<SimpleGrantedAuthority> authorities = adminRightsPostgreDAO.getAllAuthoritiesByAdminId(adminId);
		authorities.add(new SimpleGrantedAuthority(SecurityFinals.DEFAULT_AUTHORITY));
		return authorities;
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
