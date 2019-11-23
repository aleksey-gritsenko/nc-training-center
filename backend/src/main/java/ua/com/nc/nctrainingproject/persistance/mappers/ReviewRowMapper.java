package ua.com.nc.nctrainingproject.persistance.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.com.nc.nctrainingproject.models.Review;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.ReviewQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewRowMapper implements RowMapper<Review> {

	@Override
	public Review mapRow(ResultSet resultSet, int i) throws SQLException {
		Review review = new Review();
		review.setUserId(resultSet.getInt(ReviewQuery.USER_ID));
		review.setAdminId(resultSet.getInt(ReviewQuery.ADMIN_ID));
		review.setBookId(resultSet.getInt(ReviewQuery.BOOK_ID));
		review.setText(resultSet.getString(ReviewQuery.TEXT));
		review.setGrade(resultSet.getInt(ReviewQuery.GRADE));
		review.setReviewDate(resultSet.getDate(ReviewQuery.REVIEW_DATE));
		review.setStatus(resultSet.getBoolean(ReviewQuery.STATUS));
		return review;
	}
}
