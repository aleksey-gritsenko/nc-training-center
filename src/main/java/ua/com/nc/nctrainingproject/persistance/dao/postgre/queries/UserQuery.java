package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;

public class UserQuery {

  public static final String TABLE_NAME = "users";
  public static final String ID = "user_id";
  public static final String USERNAME = "user_name";
  public static final String USER_PASSWORD = "password";
  public static final String EMAIL = "email";

  public static final String GET_BY_USERNAME = "SELECT * FROM " + TABLE_NAME
    + " WHERE " + USERNAME + " =(?)";

  public static final String CREATE_USER = "INSERT INTO " + TABLE_NAME
    + " (" + USERNAME + "," + USER_PASSWORD + "," + EMAIL + ")" + " VALUES(?,?,?)";

  public static final String GET_ALL = "SELECT * FROM " + TABLE_NAME;
  public static final String UPDATE_PASSWORD = "UPDATE " + TABLE_NAME + " set " + USER_PASSWORD + "=(?)" + " where " + USERNAME + "=(?)";

  public static final String GET_EMAIL_BY_USERNAME =
    "SELECT *  FROM " + TABLE_NAME + " WHERE " + USERNAME + "=(?)" + " AND " + EMAIL + " =(?)";

  public static final String UPDATE_BY_USERNAME = "UPDATE " + TABLE_NAME + " SET " +
    USERNAME + " =(?), " +
    USER_PASSWORD + " =(?), " +
    EMAIL + " =(?)" +
   " WHERE " + USERNAME + " =(?)";
}
