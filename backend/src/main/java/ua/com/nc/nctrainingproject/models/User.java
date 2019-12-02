package ua.com.nc.nctrainingproject.models;

public class User extends Entity {
    private String userName;
    private String userPassword;
    private String email;
    private String recoverCode;
    private String userRole;
    private boolean verified;
    private boolean activated;

    public User(int id, String userName, String userPassword, String email, String recoverCode, String userRole) {
        super(id);
        this.userName = userName;
        this.userPassword = userPassword;
        this.email = email;
        this.recoverCode = recoverCode;
        this.userRole = userRole;
    }

    public User() {
        super();
    }

    public User(String newLogin, String newPassword, String newEmail) {
        this.userName = newLogin;
        this.userPassword = newPassword;
        this.email = newEmail;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
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

    public String getRecoverCode() {
        return recoverCode;
    }

    public void setRecoverCode(String recoverCode) {
        this.recoverCode = recoverCode;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", email='" + email + '\'' +
                ", recoverCode='" + recoverCode + '\'' +
                ", userRole='" + userRole + '\'' +
                ", verified=" + verified +
                ", activated=" + activated +
                '}';
    }
}
