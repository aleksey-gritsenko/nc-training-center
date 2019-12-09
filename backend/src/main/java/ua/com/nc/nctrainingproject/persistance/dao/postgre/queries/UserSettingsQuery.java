package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;

public class UserSettingsQuery {
    private static final String TABLE_NAME = "usersettings";

    public static final String SETTINGS_LIST_ID = "settings_list_id";
    public static final String USER_ID = "user_id";

    public static final String GET_SETTINGS_LIST_ID = "SELECT " + SETTINGS_LIST_ID +
            " FROM " + TABLE_NAME + " WHERE " + USER_ID+ " = (?)";





}
