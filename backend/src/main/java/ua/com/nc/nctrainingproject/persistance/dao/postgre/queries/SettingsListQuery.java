package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;

public class SettingsListQuery {
    private static final String TABLE_NAME = "settings_list";
    public static final String SETTINGS_LIST_ID = "settings_list_id";
    public static final String SUBSCRIBE_ON_FRIENDS = "subscribe_on_friends";
    public static final String ACHIEVEMENTS = "achievements";
    public static final String BOOK_NOTIFICATION = "book_notification";
    public static final String SUBSCRIBE_ON_FRIEND_REVIEW = "subscribe_on_friend_review";
    public static final String NOTIFY_ABOUT_NEW_FRIENDS = "notify_about_new_friends";
    public static final String NOTIFY_ABOUT_ACHIEVEMENTS = "notify_about_achievements";


    public static final String GET_USER_SETTINGS = "SELECT * FROM " + TABLE_NAME + " WHERE " +
            SETTINGS_LIST_ID + " = (?)";
}
