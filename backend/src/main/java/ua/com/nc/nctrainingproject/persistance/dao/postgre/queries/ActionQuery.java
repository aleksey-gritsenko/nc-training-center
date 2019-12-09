package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;

public class ActionQuery {
	public static final String TABLE_NAME = "actions";
	public static final String ACTION_ID = "id";
	public static final String USER_ID = "user_id";
	public static final String ACTION_TYPE_ID = "action_type_id";

	public static final String GET_ALL_ACTIONS = "SELECT * FROM " + TABLE_NAME;

	public static final String GET_BY_ACTION_ID = "SELECT * FROM " + TABLE_NAME
			+ " WHERE " + ACTION_ID + " =(?)";

	public static final String GET_BY_USER_ID = "SELECT * FROM " + TABLE_NAME
			+ " WHERE " + USER_ID + " =(?)";

	public static final String GET_BY_ACTION_TYPE_ID = "SELECT * FROM " + TABLE_NAME
			+ " WHERE " + ACTION_TYPE_ID + " =(?)";

	public static final String GET_BY_USER_TYPE_ID = "SELECT * FROM " + TABLE_NAME
			+ " WHERE " + ACTION_TYPE_ID + " =(?) " + " AND " + USER_ID + "=(?)";

	public static final String DELETE_BY_ACTION_ID = "DELETE FROM " + TABLE_NAME
			+ " WHERE " + ACTION_ID + " =(?) ";

	public static final String UPDATE_ACTION_BY_ID =
			"UPDATE " + TABLE_NAME + " SET " + USER_ID + "=(?)" +
					ACTION_TYPE_ID + "=(?) " + " WHERE " + ACTION_ID + "=(?)";

	public static final String CREATE_ACTION = "INSERT INTO " + TABLE_NAME
			+ " (" + USER_ID + "," + ACTION_TYPE_ID + ")" + " VALUES(?,?)";

	public static final String GET_ALL_BY_USER_ID_BY_ACTION_TYPE_ID = "SELECT * FROM " + TABLE_NAME +
				" WHERE " + USER_ID + " =(?) AND " + ACTION_TYPE_ID + " =(?)";
}
