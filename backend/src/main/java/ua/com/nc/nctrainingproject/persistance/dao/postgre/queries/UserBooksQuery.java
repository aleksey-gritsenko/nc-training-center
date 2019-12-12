package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;

public class UserBooksQuery {
	public static final String TABLE_NAME = "user_books";
	public static final String ID = "id";
	public static final String USER_ID = "user_id";
	public static final String BOOK_ID = "book_id";
	public static final String IS_READ = "is_read";
	public static final String IS_FAVOURITE = "is_favorite";

	public static final String GET_ALL_USER_BOOKS_ID = "SELECT " + BOOK_ID + " FROM " + TABLE_NAME + " WHERE "
			+ USER_ID + " =(?)";

	public static final String GET_ALL_FAVOURITE_BOOKS_ID = GET_ALL_USER_BOOKS_ID + " AND " + IS_FAVOURITE + " ='true'";

	public static final String GET_ALL_READ_BOOKS_ID = GET_ALL_USER_BOOKS_ID + " AND " + IS_READ + " ='true'";

	public static final String ADD_BOOK_TO_USER = "INSERT INTO " + TABLE_NAME + "(" + USER_ID + "," + BOOK_ID + ","
			+ IS_READ + "," + IS_FAVOURITE + ")" + " VALUES (?,?,?,?)";

	public static final String MARK_BOOK_AS_READ = "UPDATE " + TABLE_NAME + " SET " + IS_READ + " = 'true' " +
			" WHERE " + USER_ID + " =(?)" + " AND " + BOOK_ID + " =(?)";

	public static final String MARK_BOOK_AS_FAVOURITE = "UPDATE " + TABLE_NAME + " SET " + IS_FAVOURITE +
			" = 'true' " + " WHERE " + USER_ID + " =(?)" + " AND " + BOOK_ID + " =(?)";

	public static final String REMOVE_FROM_READ = "UPDATE " + TABLE_NAME + " SET " + IS_READ + " = 'false' " +
			" WHERE " + USER_ID + " =(?)" + " AND " + BOOK_ID + " =(?)";

	public static final String REMOVE_FROM_FAVOURITE = "UPDATE " + TABLE_NAME + " SET " + IS_FAVOURITE +
			" = 'false' " + " WHERE " + USER_ID + " =(?)" + " AND " + BOOK_ID + " =(?)";

	public static final String DELETE_BOOK_FROM_ADDED = "DELETE FROM  " + TABLE_NAME + " WHERE " + USER_ID +
			" =(?)" + " AND " + BOOK_ID + " =(?)";

	public static final String GET_USER_BOOK_BY_USER_AND_BOOK_ID = "SELECT * FROM " + TABLE_NAME + " WHERE " +
			USER_ID + "= (?)" + " AND " + BOOK_ID + "=(?)";
}
