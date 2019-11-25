package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;

public class AnnouncementQuery {
	public static final String TABLE_NAME = "announcement";
	public static final String ANNOUNCEMENT_ID = "announcement_ID";
	public static final String DESCRIPTION = "description";
	public static final String ANNOUNCEMENT_DATE = "announcement_date";
	public static final String BOOK_ID = "book_id";
	public static final String PRIORITY = "priority";
	public static final String OWNER_ID = "owner_id";
	public static final String STATUS = "status";

	public static final String UNPUBLISHED = "UNPUBLISHED";
	public static final String PUBLISHED = "PUBLISHED";

	public static final String LOW_PRIORITY = "LOW";
	public static final String MEDIUM_PRIORITY = "MEDIUM";
	public static final String HIGH_PRIORITY = "HIGH";

	public static final String GET_ANNOUNCEMENTS = "SELECT * FROM " + TABLE_NAME;

	public static final String GET_UNPUBLISHED_ANNOUNCEMENTS = "SELECT * FROM " + TABLE_NAME +
			" WHERE " + STATUS + " = '" + UNPUBLISHED + "'";

	public static final String GET_PUBLISHED_ANNOUNCEMENTS = "SELECT * FROM " + TABLE_NAME +
			" WHERE " + STATUS + " = '" + PUBLISHED + "'";

	public static final String GET_ANNOUNCEMENT_BY_ID = "SELECT * FROM " + TABLE_NAME
			+ " WHERE " + ANNOUNCEMENT_ID + " =(?)";

	// is not used yet
	public static final String GET_ANNOUNCEMENT_BY_DATE = "SELECT * FROM " + TABLE_NAME
			+ " WHERE " + ANNOUNCEMENT_DATE + " =(?)";

	// is not used yet
	public static final String GET_ANNOUNCEMENT_BY_DATES = "SELECT * FROM " + TABLE_NAME
			+ " WHERE " + ANNOUNCEMENT_DATE + " BETWEEN (?) AND (?)";

	public static final String CREATE_ANNOUNCEMENT = "INSERT INTO " + TABLE_NAME
			+ " (" + DESCRIPTION + "," + ANNOUNCEMENT_DATE + "," + BOOK_ID + "," + PRIORITY + ","
			+ OWNER_ID + "," + STATUS + ")" + " VALUES(?,?,?,?,?,?)";

	public static final String PUBLISH_ANNOUNCEMENT = "UPDATE " + TABLE_NAME + " SET " +
			STATUS + "='" + PUBLISHED + "' WHERE " + ANNOUNCEMENT_ID + "=(?)";

	public static final String DELETE_ANNOUNCEMENT_BY_ID = "DELETE FROM " + TABLE_NAME +
			" WHERE " + ANNOUNCEMENT_ID + " =(?)";

	public static final String UPDATE_ANNOUNCEMENT = "UPDATE " + TABLE_NAME + " SET " +
			DESCRIPTION + "=(?), " + ANNOUNCEMENT_DATE + "=(?), " + BOOK_ID + "=(?), "
			+ PRIORITY + "=(?)," + OWNER_ID + "=(?), " + STATUS + "=(?)"
			+ " WHERE " + ANNOUNCEMENT_ID + "=(?)";
}