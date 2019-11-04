package ua.com.nc.nctrainingproject.models;

public class Admin {
    private String userName;
    private String userPassword;
    private String email;
    private String adminRole;

    public Admin(String userName, String userPassword, String email, String adminRole) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.email = email;
        this.adminRole = adminRole;
    }

    public Admin() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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
                "userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
