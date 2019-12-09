package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;

public class ActionTypeQuery {
	public static final String TABLE_NAME = "action_type";
	public static final String ACTION_TYPE_ID = "action_type_id";
	public static final String ACTION_NAME = "name";
	public static final String ENTITY = "entity";

	public static final String GET_ALL_ACTION_TYPES = "SELECT * FROM " + TABLE_NAME;

	public static final String GET_BY_ACTION_TYPE_ID = GET_ALL_ACTION_TYPES
			+ " WHERE " + ACTION_TYPE_ID + " =(?)";

	public static final String GET_ALL_ACTION_TYPES_NAMES = "SELECT " + ACTION_NAME + " FROM " + TABLE_NAME;

	public static final String GET_BY_ACTION_NAME = GET_ALL_ACTION_TYPES
			+ " WHERE " + ACTION_NAME + " =(?)";

	public static final String GET_BY_ENTITY = GET_ALL_ACTION_TYPES
			+ " WHERE " + ENTITY + " =(?)";

	public static final String DELETE_BY_ACTION_TYPE_ID = "DELETE FROM " + TABLE_NAME
			+ " WHERE " + ACTION_TYPE_ID + " =(?) ";

	public static final String CREATE_ACTION_TYPE = "INSERT INTO " + TABLE_NAME
			+ " (" + ACTION_TYPE_ID + ", " + ACTION_NAME + ", " + ENTITY + ")" + " VALUES(?,?,?)";

	public static final String UPDATE_ACTION_TYPE_BY_ID =
			"UPDATE " + TABLE_NAME + " SET " + ACTION_NAME + "=(?)" +
					" SET " + ENTITY + "=(?) " +
					" WHERE " + ACTION_TYPE_ID + "=(?)";
	public static final String GET_BY_ACTION_NAME_GENRE = GET_ALL_ACTION_TYPES
			+ " WHERE " + ACTION_NAME + " =(?)" +" AND ";

}

