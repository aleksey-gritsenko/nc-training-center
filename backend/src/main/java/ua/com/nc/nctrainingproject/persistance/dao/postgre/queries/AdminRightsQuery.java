package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;

public class AdminRightsQuery {

    public static final String TABLE_NAME = "adminrights";
    public static final String ADMIN_ID = "admin_id";
    public static final String RIGHT_ID = "right_id";

    public static final String GET_ADMIN_ID_BY_RIGHT_ID= "SELECT " + ADMIN_ID + " FROM " + TABLE_NAME +
            " WHERE "+ RIGHT_ID + " =(?);";
    public static final String GET_RIGHT_ID_BY_ADMIN_ID= "SELECT " + RIGHT_ID + " FROM " + TABLE_NAME +
            " WHERE "+ ADMIN_ID + " =(?);";
    public static final String DELETE_BY_ADMIN_ID = "DELETE FROM" + TABLE_NAME + " WHERE" + ADMIN_ID + " =(?);";
    public static final String CREATE_PAIR = "INSERT INTO " + TABLE_NAME
            + " (" + ADMIN_ID + "," + RIGHT_ID  + ")" + " VALUES(?,?)";

}
