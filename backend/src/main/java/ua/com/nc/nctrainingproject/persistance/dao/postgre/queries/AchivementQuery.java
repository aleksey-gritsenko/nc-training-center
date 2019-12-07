package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;

public class AchivementQuery {
    public static final String TABLE_NAME = "achievment";
    public static final String ACHIEVEMENT_NAME = "achievment_name";
    public static final String ACTION = "action_type_id";
    public static final String GENRE_ID = "genre_id";
    public static final String COUNT = "count";
    public static final String ENTITY = "entity";
    public static final String CREATE_ACHIEVEMENT=
            "INSERT INTO  "+TABLE_NAME +" " +
                    "( "+ACHIEVEMENT_NAME+" , " +ACTION + " , "+GENRE_ID +" , "+COUNT+" , " + ENTITY+" ) "
                    +" VALUES (?,?,?,?,?)";



}
