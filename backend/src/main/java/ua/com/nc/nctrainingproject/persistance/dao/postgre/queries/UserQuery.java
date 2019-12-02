package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;

public class  UserQuery {

	public static final String TABLE_NAME = "users";
	public static final String ID = "user_id";
	public static final String USERNAME = "user_name";
	public static final String USER_PASSWORD = "password";
	public static final String EMAIL = "email";
	public static final String ROLE = "role";
	public static final String STATUS = "activated";

	public static final String VERIFIED = "verified";
	public static final String ACTIVATED = "activated";

	public static final String GET_ALL_USERS = "SELECT * FROM " + TABLE_NAME;

	public static final String GET_BY_USERNAME = "SELECT * FROM " + TABLE_NAME
			+ " WHERE " + USERNAME + " =(?)";

	public static final String CREATE_USER = "INSERT INTO " + TABLE_NAME
			+ " (" + USERNAME + "," + USER_PASSWORD + "," + EMAIL + "," + ROLE + ")" + " VALUES(?,?,?,?)";

	public static final String GET_ALL = "SELECT * FROM " + TABLE_NAME;
	public static final String UPDATE_PASSWORD_BY_EMAIL =
			"UPDATE " + TABLE_NAME + " set " + USER_PASSWORD + "=(?)" + " where " + EMAIL + "=(?)";

	public static final String GET_EMAIL =
			"SELECT * FROM " + TABLE_NAME + " WHERE " + EMAIL + " =(?)";

	public static final String UPDATE_BY_USERNAME = "UPDATE " + TABLE_NAME + " SET " +
			USERNAME + " =(?), " +
			USER_PASSWORD + " =(?), " +
			EMAIL + " =(?)" +
			" WHERE " + USERNAME + " =(?)";

	public static final String GET_BY_ID = GET_ALL_USERS + " WHERE " + ID + " =(?)";
	public static final String GET_BY_EMAIL = GET_ALL_USERS + " WHERE " + EMAIL + " =(?)";

	public static final String CREATE_ADMIN = "INSERT INTO " + TABLE_NAME
			+ " (" + USERNAME + "," + USER_PASSWORD + "," + EMAIL + "," + ROLE + ")" + " VALUES(?,?,?,?)";

	public static final String GET_USERS_BY_ROLE = "SELECT * FROM " + TABLE_NAME + " WHERE " + ROLE + " =(?)";

	public static final String UPDATE_STATUS_BY_EMAIL = "UPDATE " + TABLE_NAME + " SET " +
			STATUS + " =(?)  " +

			" WHERE " + EMAIL + " =(?)";

	public static final String CHECK_ACCOUNT_ACTIVATION = "DELETE FROM " + TABLE_NAME + " WHERE verified = FALSE AND " +
			"date_part(\'hour\', CURRENT_TIMESTAMP - reg_time) >= (?)";

	public static final String DEACTIVATE_ACCOUNT = "UPDATE " + TABLE_NAME + " SET " + ACTIVATED + " =(?) " +
			"WHERE " + ID + " =(?)";
}

