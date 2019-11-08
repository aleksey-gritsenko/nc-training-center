package ua.com.nc.nctrainingproject.models;

public class Admin {

	private String adminName;
	private String adminPassword;
	private String email;
	private String adminRole;

	public Admin(String adminName, String adminPassword, String email, String adminRole) {
		this.adminName = adminName;
		this.adminPassword = adminPassword;
		this.email = email;
		this.adminRole = adminRole;
	}

	public Admin() {

	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdminRole() {
		return adminRole;
	}

	public void setAdminRole(String adminRole) {
		this.adminRole = adminRole;
	}

	@Override
	public String toString() {
		return "Admin{" +
				"adminName='" + adminName + '\'' +
				", adminPassword='" + adminPassword + '\'' +
				", email='" + email + '\'' +
				", adminRole='" + adminRole + '\'' +
				'}';
	}
}
