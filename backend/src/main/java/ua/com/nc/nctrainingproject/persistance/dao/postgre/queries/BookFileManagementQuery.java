package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;

public class BookFileManagementQuery {
    public static final String BOOK_IMAGES_TABLE = "book_images";
    public static final String BOOK_FILES_TABLE = "book_files";
    public static final String BOOK_ID = "book_id";
    public static final String FILE = "file";

    public static final String GET_BOOK_IMAGES = "SELECT * FROM " + BOOK_IMAGES_TABLE;
    public static final String GET_BOOK_FILES = "SELECT * FROM " + BOOK_FILES_TABLE;

    public static final String GET_BOOK_FILE = "SELECT * FROM " + BOOK_FILES_TABLE + " WHERE " +
            BOOK_ID + "=(?)";
    public static final String GET_BOOK_IMAGE = "SELECT * FROM " + BOOK_IMAGES_TABLE + " WHERE " +
            BOOK_ID + "=(?)";

    public static final String INSERT_BOOK_IMAGE = "INSERT INTO " + BOOK_IMAGES_TABLE + " (" + BOOK_ID + ", " + FILE
            + ") VALUES (?,?)";
    public static final String INSERT_BOOK_FILE = "INSERT INTO " + BOOK_FILES_TABLE + " (" + BOOK_ID + ", " + FILE
            + ") VALUES (?,?)";

    public static final String UPDATE_BOOK_IMAGE = "UPDATE " + BOOK_IMAGES_TABLE + " SET " + FILE +
            "=(?) WHERE " + BOOK_ID + "=(?)";
    public static final String UPDATE_BOOK_FILE = "UPDATE " + BOOK_FILES_TABLE + " SET " + FILE +
            "=(?) WHERE " + BOOK_ID + "=(?)";

    public static final String DELETE_BOOK_IMAGE = "DELETE FROM " + BOOK_IMAGES_TABLE + " WHERE " + BOOK_ID + "=(?)";
    public static final String DELETE_BOOK_FILE = "DELETE FROM " + BOOK_FILES_TABLE + " WHERE " + BOOK_ID + "=(?)";
}
