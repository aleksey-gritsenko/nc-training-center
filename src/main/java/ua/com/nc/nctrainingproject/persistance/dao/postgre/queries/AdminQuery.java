package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;

public class AdminQuery {

    public static final String TABLE_NAME = "admins";
    public static final String NAME = "name";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String ROLE = "role";

    public static final String GET_ADMIN_BY_NAME = "SELECT * FROM " + TABLE_NAME + " WHERE name = (?)";
    public static final String CREATE_ADMIN = "INSERT INTO  " + TABLE_NAME +
            " (" + NAME + "," + PASSWORD + "," + EMAIL + "," + ROLE + ")" + " VALUES(?,?,?,?)";
}
