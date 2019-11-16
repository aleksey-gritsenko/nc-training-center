package ua.com.nc.nctrainingproject.models;

public class User extends Entity {
  private String userName;
  private String userPassword;
  private String email;
  private String recoverCode;
  private int userRole;

  public User(int id, String userName, String userPassword, String email, String recoverCode, int userRole) {
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
    this.userName=newLogin;
    this.userPassword=newPassword;
    this.email=newEmail;
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

  public int getUserRole() {
    return userRole;
  }

  public void setUserRole(int userRole) {
    this.userRole = userRole;
  }
}
