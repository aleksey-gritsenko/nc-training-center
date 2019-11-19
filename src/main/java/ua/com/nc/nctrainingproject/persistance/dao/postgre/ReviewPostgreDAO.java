package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.Review;
import ua.com.nc.nctrainingproject.persistance.dao.ReviewDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.ReviewQuery;
import ua.com.nc.nctrainingproject.persistance.mappers.ReviewRowMapper;

import java.util.List;

@Repository
public class ReviewPostgreDAO implements ReviewDAO {
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public ReviewPostgreDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Review> getReviewsOfBook(int book_id) {
		try {
			return jdbcTemplate.query(ReviewQuery.GET_REVIEWS_OF_BOOK, new Object[]{book_id}, new ReviewRowMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public void createReview(Review review) {
		jdbcTemplate.update(ReviewQuery.CREATE_REVIEW,
				review.getUserId(),
				review.getBookId(),
				review.getText(),
				review.getGrade(),
				review.getAdminId());
	}
}


