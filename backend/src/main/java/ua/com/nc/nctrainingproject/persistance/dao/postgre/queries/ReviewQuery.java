package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;

public class ReviewQuery {
	public static final String REVIEW_ID = "review_id";
	public static final String TABLE_NAME = "review";
	public static final String USER_ID = "user_id";
	public static final String BOOK_ID = "book_id";
	public static final String TEXT = "text";
	public static final String REVIEW_DATE = "review_date";
	public static final String GRADE = "grade";
	public static final String ADMIN_ID = "admin_id";
	public static final String STATUS = "status";

	public static final String GET_REVIEWS_OF_BOOK = "SELECT * FROM " + TABLE_NAME + " WHERE " + BOOK_ID + " =(?)";
	public static final String CREATE_REVIEW = "INSERT INTO " + TABLE_NAME +
			" (" + USER_ID + "," + BOOK_ID + "," + TEXT + "," + REVIEW_DATE + "," + GRADE  + "," + STATUS +  " )"
			+ " VALUES(?,?,?,?,?,?)";
	public static final String ACCEPT_REVIEW = "UPDATE " + TABLE_NAME +
			" SET " + STATUS + " =(?), " + ADMIN_ID + " =(?)  WHERE " + REVIEW_ID + " =(?)";

	public static final String GET_ACCEPTED_REVIEWS_OF_BOOK = "SELECT * FROM " + TABLE_NAME +
			" WHERE " +  STATUS + "=(?) AND " + BOOK_ID + " =(?)";

}

