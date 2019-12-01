package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;

public class BookQuery {

    public static final String TABLE_NAME = "books";
    public static final String BOOK_ID = "book_id";
    public static final String TITLE = "name";
    public static final String HEADER = "header";
    public static final String AUTHOR = "author_name";
    public static final String OVERVIEW = "overview";
    public static final String PHOTO = "photo_id";
    public static final String FILE = "file";
    public static final String STATUS = "status";
    public static final String GENRE_ID = "genre_id";

    public static final String GENRE = "genre_name";
    public static final String GENRES_TABLE = "genres";

    public static final String REVIEW_TABLE = "review";
    public static final String GRADE = "grade";

    public static final String GET_ALL = "SELECT *" + " FROM " + TABLE_NAME;

    public static final String GET_BOOK = GET_ALL + " WHERE " + TABLE_NAME + "." + BOOK_ID + " =(?)";

    public static final String GET_BOOK_BY_ID = "SELECT * FROM " + TABLE_NAME
            + " WHERE " + BOOK_ID + " =(?)";

    public static final String GET_BOOK_BY_TITLE = "SELECT * FROM " + TABLE_NAME
            + " WHERE " + HEADER + " =(?)";

    public static final String GET_BOOK_BY_STATUS = "SELECT * FROM " + TABLE_NAME
            + " WHERE " + STATUS + " =(?)";

    public static final String CREATE_BOOK = "INSERT INTO " + TABLE_NAME
            + " (" + HEADER + ","
            + OVERVIEW + "," + FILE + "," + STATUS + "," + GENRE_ID + "," + PHOTO + ")" + " VALUES(?,?,?,?,?,?)";

    public static final String GET_ALL_BOOKS = "SELECT * FROM " + TABLE_NAME;

    public static final String UPDATE_BOOK = "UPDATE " + TABLE_NAME + " SET " +
            HEADER + "=(?), " + OVERVIEW + "=(?), " + FILE + "=(?), " + STATUS + "=(?)," + GENRE_ID + "=(?),"
            + PHOTO + "=(?) " + " WHERE " + BOOK_ID + "=(?)";

    public static final String DELETE_BOOK_BY_ID = "DELETE FROM " + TABLE_NAME + " WHERE " + BOOK_ID + " =(?)";

    public static final String GET_BOOKS_BY_TITLE = "SELECT * FROM " + TABLE_NAME
            + " WHERE " + HEADER + " =(?)";

    public static final String GET_BOOKS_BY_STATUS = "SELECT * FROM " + TABLE_NAME
            + " WHERE " + STATUS + " =(?)";

    public static final String GET_GENRE_BY_BOOK_ID = "SELECT " + GENRES_TABLE + "." + GENRE+"," + GENRES_TABLE +"." + GENRE_ID + " FROM " + GENRES_TABLE + " JOIN " + TABLE_NAME
            + " ON " + TABLE_NAME + "." + GENRE_ID + " = " + GENRES_TABLE + "." + GENRE_ID +
            " WHERE " +TABLE_NAME + "."+ BOOK_ID  + "=(?)";

    public static final String GET_BOOKS_FILTRATION =
            "select " + TABLE_NAME + "." + BOOK_ID + " , " + HEADER + ", " + AUTHOR + ", " + OVERVIEW + " ," + STATUS + " ," + PHOTO + ", " + FILE + ", " + GENRE + " " +
                    " from " + TABLE_NAME + " join " + GENRES_TABLE + " ON " + TABLE_NAME + "." + GENRE_ID + " = " + GENRES_TABLE + "." + GENRE_ID +
                    " join " + AuthorBookQuery.TABLE_NAME + " on " + TABLE_NAME + "." + BOOK_ID + " = " + AuthorBookQuery.TABLE_NAME + "." + AuthorBookQuery.BOOK_ID +
                    " join " + AuthorQuery.TABLE_NAME + " on " + AuthorBookQuery.TABLE_NAME + "." + AuthorBookQuery.AUTHOR_ID + " = " + AuthorQuery.TABLE_NAME + "." + AuthorQuery.ID + " where ";

    public static final String CONDITIONS_GENRES = GENRE + "=(?)";
    public static final String CONDITIONS_NAME = HEADER + " LIKE " + "(?)";
    public static final String CONDITION_AUTHOR = AUTHOR + " =(?)";

    public static final String GET_MOST_RATED_BOOKS = "select distinct " + TABLE_NAME + "." + BOOK_ID + " , " + HEADER + ", " + AUTHOR + ", " + OVERVIEW + " ," + STATUS + " ," + PHOTO + ", " + FILE + ", " + GENRE + " " +
            " from " + TABLE_NAME + " join " + GENRES_TABLE + " ON " + TABLE_NAME + "." + GENRE_ID + " = " + GENRES_TABLE + "." + GENRE_ID +
            " join " + AuthorBookQuery.TABLE_NAME + " on " + TABLE_NAME + "." + BOOK_ID + " = " + AuthorBookQuery.TABLE_NAME + "." + AuthorBookQuery.BOOK_ID +
            " join " + AuthorQuery.TABLE_NAME + " on " + AuthorBookQuery.TABLE_NAME + "." + AuthorBookQuery.AUTHOR_ID + " = " + AuthorQuery.TABLE_NAME + "." + AuthorQuery.ID + " where " +
            TABLE_NAME + "." +  BOOK_ID  + " IN ( SELECT " + REVIEW_TABLE+ "." + BOOK_ID+
            " FROM " + TABLE_NAME + " JOIN " + REVIEW_TABLE + " ON " + REVIEW_TABLE + "." + BOOK_ID+ "="+TABLE_NAME + "." +
            BOOK_ID + " GROUP BY " + REVIEW_TABLE + "." + BOOK_ID +
            " ORDER BY avg(" + REVIEW_TABLE  + "." + GRADE + ") DESC)";
 }
