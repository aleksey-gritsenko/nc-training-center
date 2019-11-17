package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;

public class UserBookQuery {
  public static final String USER_BOOK_ID = "id";
  public static final String TABLE_NAME  = "user_books";
  public static final String USER_ID  = "user_id";
  public static final String BOOK_ID = "book_id";
  public static final String IS_READ = "is_read";
  public static final String IS_FAVORITE = "is_favorite";
  public static final String ADD_BOOK = "INSERT INTO " + TABLE_NAME  + "(" +
    USER_ID + "," + BOOK_ID + "," + IS_READ + "," + IS_FAVORITE + " )" +
    "VALUES" + " (?,?,false,false)";
}

