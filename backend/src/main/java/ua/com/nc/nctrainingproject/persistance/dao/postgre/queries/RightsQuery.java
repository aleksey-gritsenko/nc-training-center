package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;

public class RightsQuery {
    private static final String TABLE_NAME = "rights";

    private static final String RIGHT_ID = "right_id";
    private static final String DESCRIPTION = "description";

    public static final String GET_DESCRIPTION_BY_RIGHT_ID = "SELECT " + DESCRIPTION + " FROM " + TABLE_NAME +
            " WHERE " + RIGHT_ID + " =(?)";

}
