package ua.com.nc.nctrainingproject.security;

public class SecurityFinals {
    public static final String DEFAULT_AUTHORITY = "DEFAULT_AUTHORITY";

    public static final String PUBLISH_ANNOUNCEMENT = "publish announcement";
    public static final String PUBLISH_REVIEWS = "publish reviews";
    public static final String PUBLISH_BOOK_OVERWIEWS = "publish bookOverviews";


    public static final String LOGIN_URL = "/login/**";
    public static final String FRIENDS_ACCEPT_URL = "/friends/accept/";
    public static final String GET_SETTINGS_URL = "/getSettings/";
    public static final String UPDATE_SETTINGS_URL = "/updateSettings/";
    public static final String USER_BOOK_URL = "/userBook/**";
    public static final String ANNOUNCEMENT_PUBLISH_URL = "/announcements/publish/**";
    public static final String REVIEW_ACCEPT_URL = "/review/accept/**";
    public static final String REVIEW_NOT_ACCEPTED_URL = "/review/notaccepted/**";
    public static final String BOOK_UPDATE_URL = "/book/update/**";
    public static final String BOOK_ADD_FILE_URL = "/book/addFile/**";
    public static final String BOOK_ADD_IMAGE_URL = "/book/addImage/**";
}
