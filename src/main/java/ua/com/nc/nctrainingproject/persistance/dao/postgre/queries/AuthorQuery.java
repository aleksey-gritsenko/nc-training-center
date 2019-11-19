package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;

public class AuthorQuery {
	public static final String TABLE_NAME = "authors";
	public static final String ID = "id";
	public static final String AUTHOR_NAME = "author_name";
	public static final String GET_ALL_AUTHORS = "Select * From " + TABLE_NAME;
	public static final String GET_AUTHOR_BY_ID = GET_ALL_AUTHORS + " WHERE " + ID + " =(?)";
	public static final String DELETE_BY_ID = "DELETE FROM " + TABLE_NAME + " WHERE " + ID + " =(?)";

}
