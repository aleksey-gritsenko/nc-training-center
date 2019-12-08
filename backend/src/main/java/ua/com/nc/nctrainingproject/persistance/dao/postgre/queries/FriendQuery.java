package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;

public class FriendQuery {
    public static final String TABLE_NAME = "friends";
    public static final String SENDER_ID = "sender_id";
    public static final String RECIEVER_ID = "reciever_user_id";
    public static final String REQUEST_STATUS = "request_status";
    public static final String SEND_REQUEST = "INSERT INTO " + TABLE_NAME + " ( " + SENDER_ID + " , " + RECIEVER_ID + " , " + REQUEST_STATUS + " ) " + " VALUES(?,?,?) ;";

    public static final String ACCEPT_REQUEST = "UPDATE " + TABLE_NAME + " SET " + REQUEST_STATUS + " =(?)" + " WHERE " + SENDER_ID + " =(?)" + " AND " + RECIEVER_ID + " =(?);";

    public static final String GET_ALL_FRIENDS = "SELECT "
            + UserQuery.ID + " , " + UserQuery.USERNAME + " , " + UserQuery.USER_PASSWORD + " , " + UserQuery.EMAIL + " , " + UserQuery.ROLE + " , " + UserQuery.VERIFIED+" , " + UserQuery.ACTIVATED+"  FROM " + TABLE_NAME + " join " + UserQuery.TABLE_NAME +
            " on " + RECIEVER_ID + " = " + UserQuery.ID +
            " WHERE " + REQUEST_STATUS + " =(?)" + " AND " + SENDER_ID + " =(?)" + " union " +
            " SELECT "
            + UserQuery.ID + " , " + UserQuery.USERNAME + " , " + UserQuery.USER_PASSWORD + " , " + UserQuery.EMAIL + " , " + UserQuery.ROLE +" , " + UserQuery.VERIFIED+ " , " + UserQuery.ACTIVATED+"  FROM " + TABLE_NAME +
            " join "
            + UserQuery.TABLE_NAME +
            " on " + SENDER_ID + " = " + UserQuery.ID +
            " WHERE " + REQUEST_STATUS + " =(?)" + "AND " + RECIEVER_ID + " =(?)";

    public static final String GET_ALL_NEW_REQUESTS = "SELECT "
            + UserQuery.ID + " , " + UserQuery.USERNAME + " , " + UserQuery.USER_PASSWORD + " , " + UserQuery.EMAIL + " , " +
            UserQuery.ROLE + " , " + UserQuery.VERIFIED+" , " + UserQuery.ACTIVATED+ "  FROM " + TABLE_NAME + " join " + UserQuery.TABLE_NAME +
            " on " + SENDER_ID + " = " + UserQuery.ID +
            " WHERE " + REQUEST_STATUS + " =(?)" + " AND " + RECIEVER_ID + " =(?)";


    public static final String GET_SENDER =  "SELECT "
            + UserQuery.ID + " , " + UserQuery.USERNAME + " , " + UserQuery.USER_PASSWORD + " , " + UserQuery.EMAIL + " , " +
            UserQuery.ROLE +" , " + UserQuery.VERIFIED+" , " + UserQuery.ACTIVATED+ "  FROM " + TABLE_NAME + " join " + UserQuery.TABLE_NAME +
            " on " + SENDER_ID + " = " + UserQuery.ID + " WHERE " + SENDER_ID + " =(?)"+ " AND " + RECIEVER_ID + " =(?)"  ;
    public static final String GET_RECIEVER =  "SELECT "
            + UserQuery.ID + " , " + UserQuery.USERNAME + " , " + UserQuery.VERIFIED+" , " + UserQuery.USER_PASSWORD + " , " + UserQuery.EMAIL + " , " +
            UserQuery.ROLE + " , " + UserQuery.ACTIVATED+"  FROM " + TABLE_NAME + " join " + UserQuery.TABLE_NAME +
            " on " + RECIEVER_ID + " = " + UserQuery.ID + " WHERE "+ SENDER_ID + " =(?)"+ " AND " + RECIEVER_ID + " =(?)" ;



}
