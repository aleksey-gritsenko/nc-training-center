package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;

public class UserSettingsQuery {
	public static final String SETTINGS_LIST_ID = "settings_list_id";
	public static final String USER_ID = "user_id";
    private static final String TABLE_NAME = "usersettings";
	public static final String GET_SETTINGS_LIST_ID = "SELECT " + SETTINGS_LIST_ID +
			" FROM " + TABLE_NAME + " WHERE " + USER_ID + " = (?)";
	public static final String CREATE = "insert into usersettings (user_id,settings_list_id) values((?),(?))"; //"INSERT INTO "+TABLE_NAME+"(user_id,settings_list_id) "+" VALUES(?,?)";


}
