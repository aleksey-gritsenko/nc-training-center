package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;

public class AuthorBookQuery {
  public static final String TABLE_NAME = "book_author";
  public static final String BOOK_ID = "book_id";
  public static final String AUTHOR_ID = "author_id";

  public static final String GET_ALL = "SELECT * FROM " + TABLE_NAME;


  public static final String GET_ALL_AUTHORS_BY_BOOK_ID = "Select " + AuthorQuery.TABLE_NAME + "." + AuthorQuery.ID +

    ", " + AuthorQuery.TABLE_NAME + "." + AuthorQuery.AUTHOR_NAME + " FROM " + TABLE_NAME + " INNER JOIN " + AuthorQuery.TABLE_NAME +
    " ON " + TABLE_NAME + "." + AUTHOR_ID + " = " + AuthorQuery.TABLE_NAME + "." + AuthorQuery.ID + " WHERE " +
    TABLE_NAME + "." + BOOK_ID + " =(?)";

/*
  public static final String GET_ALL_BOOKS_BY_AUTHOR_ID = "Select " + BookQuery.TABLE_NAME + "." + BookQuery.BOOK_ID + "," + BookQuery.TABLE_NAME + "." + BookQuery.HEADER
    + "," + BookQuery.TABLE_NAME + "." + BookQuery.OVERVIEW + "," + BookQuery.TABLE_NAME + "." + BookQuery.FILE + "," + BookQuery.TABLE_NAME + "." + BookQuery.STATUS
    + ","+ BookQuery.TABLE_NAME + "." + BookQuery.PHOTO + " FROM " + TABLE_NAME + " INNER JOIN " + BookQuery.TABLE_NAME +
    " ON " + TABLE_NAME + "." + BOOK_ID + " = " + BookQuery.TABLE_NAME + "." + BookQuery.BOOK_ID + " WHERE " + TABLE_NAME + "." + AUTHOR_ID + " =(?)";

*/
  public static final String CREATE = "INSERT INTO " + TABLE_NAME + "(" + BOOK_ID + "," + AUTHOR_ID + ")" + " VALUES(?,?)";


  public static final String DELETE_BOOKS_BY_ID = "DELETE FROM " + TABLE_NAME + " WHERE " + BOOK_ID + " =(?)";
  public static final String DELETE_AUTHORS_BY_ID = "DELETE FROM " + TABLE_NAME + " WHERE " + AUTHOR_ID + " =(?)";
}
