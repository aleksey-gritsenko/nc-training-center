package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;

public class AdminQuery {

    public static final String TABLE_NAME = "admins";
    public static final String ADMIN_NAME = "name";
    public static final String ADMIN_PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String ADMIN_ROLE = "role";

    public static final String GET_ADMIN_BY_NAME = "SELECT * FROM " + TABLE_NAME + " WHERE name = (?)";
    public static final String CREATE_ADMIN = "INSERT INTO  " + TABLE_NAME +
            " (" + ADMIN_NAME + "," + ADMIN_PASSWORD + "," + EMAIL + "," + ADMIN_ROLE + ")" + " VALUES(?,?,?,?)";

    public static final String GET_EMAIL_BY_NAME = "SELECT * FROM "+ TABLE_NAME +" WHERE " +ADMIN_NAME + "=(?)"
            +"AND " + EMAIL + "=(?)";
    public static final String UPDATE_PASSWORD = "UPDATE " + TABLE_NAME + " set " +
            ADMIN_PASSWORD + "=(?)" + " where " + ADMIN_NAME + "=(?)";

    public static final String GET_PASSWORD_BY_NAME = "SELECT password FROM " + TABLE_NAME + " WHERE name=(?)";


}
