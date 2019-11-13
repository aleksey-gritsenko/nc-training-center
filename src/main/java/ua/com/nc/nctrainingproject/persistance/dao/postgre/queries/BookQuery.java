package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;

public class BookQuery {

	public static final String TABLE_NAME = "books";
  public static final String TABLE_NAME_ANNOUNCEMENTS = "announcements";
	public static final String BOOK_ID = "book_id";
	public static final String TITLE = "name";
	public static final String HEADER = "header";
	public static final String AUTHOR = "author";
	public static final String OVERVIEW = "overview";
	public static final String PHOTO = "photo_id";
	public static final String FILE = "file_id";
	public static final String STATUS = "status";

	public static final String GENRE = "genre";

	public static final String GET_BOOK_BY_ID = "SELECT * FROM " + TABLE_NAME
			+ " WHERE " + BOOK_ID + " =(?)";

	public static final String GET_BOOKS_BY_TITLE = "SELECT * FROM " + TABLE_NAME
			+ " WHERE " + HEADER + " =(?)";

	public static final String GET_BOOKS_BY_AUTHOR = "SELECT * FROM " + TABLE_NAME
			+ " WHERE " + AUTHOR + " =(?)";

	public static final String GET_BOOKS_BY_STATUS = "SELECT * FROM " + TABLE_NAME
			+ " WHERE " + STATUS + " =(?)";

	public static final String GET_BOOKS_BY_GENRE = "SELECT * FROM " + TABLE_NAME
			+ " WHERE " + GENRE + " =(?)";

	public static final String CREATE_BOOK = "INSERT INTO " + TABLE_NAME
			+ " (" + TITLE + "," + HEADER + "," + AUTHOR + ","
			+ OVERVIEW + "," + PHOTO + "," + FILE +  "," + STATUS + "," + GENRE
			+ ")" + " VALUES(?,?,?,?,?,?,?,?)";

	public static final String GET_ALL_BOOKS = "SELECT * FROM " + TABLE_NAME;

	public static final String UPDATE_BOOK = "UPDATE " + TABLE_NAME + " SET " +
			TITLE + "=(?), " + HEADER + "=(?), " + AUTHOR + "=(?), " +
			OVERVIEW + "=(?), " + PHOTO + "=(?), " + FILE + "=(?), " +
			STATUS + "=(?), " + GENRE + "=(?)" + " WHERE " + BOOK_ID + "=(?)";

 /*public static final String JOIN_BOOKS_ANNOUNCEMENT =
    "select " +TITLE+HEADER+AUTHOR+OVERVIEW+PHOTO+FILE+STATUS +
      "from "+TABLE_NAME +" join"+ TABLE_NAME_ANNOUNCEMENTS +
    "    on books.book_id = announcements.book_id WHERE ";*/
  public static final String GET_BOOKS=
    "select * " +
      "from "+TABLE_NAME +" WHERE ";
  public static final String CONDITIONS_GENRES = GENRE + "=(?)";
  public static final String CONDITIONS_NAME =HEADER +" LIKE "+"(?) ";
 // public static final String CONDITIONS_NAME = HEADER +" =(?) ";
  public static final String CONDITION_AUTHOR = AUTHOR+" =(?)";
  public static final String CONDITION_ANNOUNCEMENT_DATE ="announcements.date is between"
    +"=(?)" +" AND "+"=(?)";


}
