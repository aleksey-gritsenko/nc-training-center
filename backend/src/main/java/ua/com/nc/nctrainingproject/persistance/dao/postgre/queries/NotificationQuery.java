package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;

public class NotificationQuery {
	public static final String TABLE_NAME = "notifications";
	public static final String USER_ID = "user_id";
	public static final String ACTION_ID = "action_id";

	public static final String GET_ALL_NOTIFICATIONS = "SELECT * FROM " + TABLE_NAME;

	public static final String GET_BY_USER_ID = "SELECT * FROM " + TABLE_NAME
			+ " WHERE " + USER_ID + " =(?)";

	public static final String GET_BY_ACTION_ID = "SELECT * FROM " + TABLE_NAME
			+ " WHERE " + ACTION_ID + " =(?)";

	public static final String GET_BY_USER_ACTION_ID = "SELECT * FROM " + TABLE_NAME
			+ " WHERE " + ACTION_ID + " =(?) AND " + USER_ID + " =(?)";

	public static final String DELETE_BY_USER_ACTION_ID = "DELETE FROM " + TABLE_NAME
			+ " WHERE " + ACTION_ID + " =(?) AND " + USER_ID + " =(?)";

	public static final String CREATE_NOTIFICATION = "INSERT INTO " + TABLE_NAME
			+ " (" + USER_ID + "," + ACTION_ID +")" + " VALUES(?,?)";

}
