package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;

public class AchivementQuery {
    public static final String TABLE_NAME = "achievment";
    public static final String ACHIEVEMENT_ID = "achievement_id";

    public static final String SECOND_TABLE_NAME = "user_achievement";
    public static final String USER_ID = "user_id";

    public static final String ACHIEVEMENT_NAME = "achievment_name";
    public static final String ACTION = "action_type_id";
    public static final String GENRE_ID = "genre_id";
    public static final String COUNT = "count";
    public static final String ENTITY = "entity";
    public static final String CREATE_ACHIEVEMENT=
            "INSERT INTO  "+TABLE_NAME +" " +
                    "( "+ACHIEVEMENT_NAME+" , " +ACTION + " , "+GENRE_ID +" , "+COUNT+" , " + ENTITY+" ) "
                    +" VALUES (?,?,?,?,?)";


    public static final String GET_ALL = "SELECT * FROM " + TABLE_NAME;
    public static final String GET_ALL_ACHIEVEMENT_ID_BY_USER_ID = "SELECT " + ACHIEVEMENT_ID +
            " FROM " + SECOND_TABLE_NAME + " WHERE " + USER_ID + " =(?)";
    public static final String CREATE_PAIR = "INSERT INTO "+ SECOND_TABLE_NAME + " ( "+ USER_ID+" , "+ACHIEVEMENT_ID+" ) "
            +" VALUES (?,?)";


    public static final String GET_ACHIEVEMENT_DTO_BY_ID=
            "SELECT "+ACHIEVEMENT_ID +" , "+ ACHIEVEMENT_NAME+" , " +ActionTypeQuery.ACTION_NAME + " , "+GenreQuery.GENRE_NAME +" , "+COUNT+" , " +TABLE_NAME+"."+ ENTITY+

                    " FROM " + TABLE_NAME+
                    "  JOIN " + GenreQuery.TABLE_NAME+
                    " ON " + GenreQuery.TABLE_NAME+"."+ GenreQuery.ID +" = "+TABLE_NAME+"."+ GENRE_ID+ " JOIN "+ActionTypeQuery.TABLE_NAME +" ON "
                    + ActionTypeQuery.TABLE_NAME +"."+ActionTypeQuery.ACTION_TYPE_ID+ " = "+ TABLE_NAME+"."+ACTION +" WHERE " + ACHIEVEMENT_ID + " =(?);";

    public static final String GET_ALL_ACHIEVEMENT_DTO=
            "SELECT "+ACHIEVEMENT_ID +" , "+ ACHIEVEMENT_NAME+" , " +ActionTypeQuery.ACTION_NAME + " , "+GenreQuery.GENRE_NAME +" , "+COUNT+" , " +TABLE_NAME+"."+ ENTITY+

                    " FROM " + TABLE_NAME+
                    "  JOIN " + GenreQuery.TABLE_NAME+
                    " ON " + GenreQuery.TABLE_NAME+"."+ GenreQuery.ID +" = "+TABLE_NAME+"."+ GENRE_ID+ " JOIN "+ActionTypeQuery.TABLE_NAME +" ON "
            + ActionTypeQuery.TABLE_NAME +"."+ActionTypeQuery.ACTION_TYPE_ID+ " = "+ TABLE_NAME+"."+ACTION +" ;";

}
