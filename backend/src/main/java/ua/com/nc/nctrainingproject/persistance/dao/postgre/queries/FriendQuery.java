package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;

public class FriendQuery {
    public static final String TABLE_NAME = "friends";
    public static final String SENDER_ID = "sender_id";
    public static final String RECIEVER_ID = "reciever_user_id";
    public static final String REQUEST_STATUS = "request_status";
    public static final String SEND_REQUEST = "INSERT INTO "+ TABLE_NAME+ " ( "+SENDER_ID +" , "+RECIEVER_ID+" , " +REQUEST_STATUS+" ) "+ " VALUES(?,?,?) ;";

    public static final String ACCEPT_REQUES ="UPDATE " + TABLE_NAME +" SET "+ REQUEST_STATUS +" =(?)" +" WHERE " +SENDER_ID +" =(?)"+" AND " +RECIEVER_ID +" =(?);";

    public static final String GET_ALL_FRIENDS = "SELECT *  FROM" + TABLE_NAME + " WHERE " + REQUEST_STATUS + " =(?)" +" AND " +"("+SENDER_ID +" =(?)"+" OR " +RECIEVER_ID +" =(?)"+" ) ;";
    public static final String GET_ALL_NEW_REQUESTS = "SELECT *  FROM" + TABLE_NAME + " WHERE "
            + REQUEST_STATUS + " =(?)" +" AND "  +RECIEVER_ID +" =(?)"+" ) ;";
    public static final String GET_SENDER =  "SELECT *  FROM" + TABLE_NAME + " WHERE " +SENDER_ID +" =(?)"+" AND " +RECIEVER_ID +" =(?);" ;

}
