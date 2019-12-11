package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;

public class GenreQuery {
	public static final String TABLE_NAME = "genres";
	public static final String ID = "genre_id";
	public static final String GENRE_NAME = "genre_name";

	public static final String GET_GENRE_BY_ID = "SELECT " + GENRE_NAME +
			" FROM " + TABLE_NAME + " WHERE " + ID + " =(?)";

	public static final String CREATE_GENRE = "INSERT INTO " + TABLE_NAME
			+ " (" + GENRE_NAME + ")" + " VALUES(?)";

	public static final String GET_ID_BY_GENRE = "SELECT " + ID + " FROM "
			+ TABLE_NAME + " WHERE " + GENRE_NAME + " =(?)";
	public static final String GET_ALL_GENRES = "SELECT * FROM " + TABLE_NAME;

	public static final String GET_ALL_GENRES_NAME = "SELECT " + GENRE_NAME + " FROM " + TABLE_NAME;
}
