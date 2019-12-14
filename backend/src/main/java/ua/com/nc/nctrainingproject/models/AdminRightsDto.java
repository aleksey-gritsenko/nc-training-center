package ua.com.nc.nctrainingproject.models;

import java.util.List;

public class AdminRightsDto {
	private User user;
	private List<String> rights;

	public AdminRightsDto() {
	}

	public AdminRightsDto(User user, List<String> rights) {
		this.rights = rights;
		this.user = user;
	}

	public List<String> getRights() {
		return rights;
	}

	public void setRights(List<String> rights) {
		this.rights = rights;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
