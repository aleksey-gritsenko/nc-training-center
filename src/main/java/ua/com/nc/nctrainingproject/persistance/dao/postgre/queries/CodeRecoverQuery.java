package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;

public class CodeRecoverQuery {
    public static final String TABLE_NAME = "recoverCode";
    public static final String CODE = "code";
    public static final String CODE_DATE = "code_date";
    public static final String USERNAME = "user_name";

    public static final String CREATE_CODE =  "INSERT INTO "
            + TABLE_NAME
            + " (" + CODE + "," + CODE_DATE +","+USERNAME+ ")" +
            " VALUES(?,?,?)";

    public static final String GET_CODE_BY_USERNAME ="SELECT " + CODE +" FROM "
            + TABLE_NAME + " WHERE "+ USERNAME + " =(?)";

    public static final String DELETE_CODE_BY_USERNAME ="DELETE FROM "
            + TABLE_NAME + " WHERE "+ USERNAME + " =(?)";

}
