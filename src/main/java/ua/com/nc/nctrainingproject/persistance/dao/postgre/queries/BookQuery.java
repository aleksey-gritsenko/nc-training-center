package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;

public class BookQuery {

	public static final String TABLE_NAME = "books";
	public static final String BOOK_ID = "book_id";
	public static final String TITLE = "name";
	public static final String HEADER = "header";
	public static final String AUTHOR = "author";
	public static final String OVERVIEW = "overview";
	public static final String PHOTO = "photo";
	public static final String FILE = "file";
	public static final String STATUS = "status";
	public static final String GENRE_ID = "genre_id";

	public static final String GET_BOOK_BY_ID = "SELECT * FROM " + TABLE_NAME
			+ " WHERE " + BOOK_ID + " =(?)";

	public static final String GET_BOOK_BY_TITLE = "SELECT * FROM " + TABLE_NAME
			+ " WHERE " + TITLE + " =(?)";

	public static final String GET_BOOK_BY_AUTHOR = "SELECT * FROM " + TABLE_NAME
			+ " WHERE " + AUTHOR + " =(?)";

	public static final String GET_BOOK_BY_STATUS = "SELECT * FROM " + TABLE_NAME
			+ " WHERE " + STATUS + " =(?)";

	public static final String CREATE_BOOK = "INSERT INTO " + TABLE_NAME
			+ " (" + TITLE + "," + HEADER + "," + AUTHOR + ","
			+ OVERVIEW + "," + PHOTO + "," + FILE +  "," + STATUS + ")" + " VALUES(?,?,?,?,?,?,?)";

	public static final String GET_ALL_BOOKS = "SELECT * FROM " + TABLE_NAME;

	public static final String UPDATE_BOOK = "UPDATE " + TABLE_NAME + " SET " +
			TITLE + "=(?), " + HEADER + "=(?), " + AUTHOR + "=(?), " +
			OVERVIEW + "=(?), " + PHOTO + "=(?), " + FILE + "=(?), " +
			STATUS + "=(?)" + " WHERE " + BOOK_ID + "=(?)";

}
