package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;

public class CodeRecoverQuery {
	public static final String TABLE_NAME = "recoverCode";
	public static final String CODE = "code";
	public static final String GENERATE_DATE = "generate_date";
	public static final String EMAIL = "email";

	public static final String CREATE_CODE = "INSERT INTO "
			+ TABLE_NAME
			+ " (" + CODE + "," + GENERATE_DATE + "," + EMAIL + ")" +
			" VALUES(?,?,?)";

	public static final String GET_CODE = "SELECT * FROM "
			+ TABLE_NAME + " WHERE " + CODE + " =(?)";

	public static final String DELETE_CODE = "DELETE FROM "
			+ TABLE_NAME + " WHERE " + CODE + " =(?)";
	public static final String DELETE_CODE_EMAIL = "DELETE FROM "
			+ TABLE_NAME + " WHERE " + EMAIL + " =(?)";

	public static final String DELETE_ALL = "DELETE FROM "
			+ TABLE_NAME + " WHERE 1=1";

	public static final String DELETE_ALL_HOUR = "SELECT * from recovercode WHERE generate_date < now() - interval '1 hour'";

}
