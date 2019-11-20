package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.Review;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.ReviewPostgreDAO;

import java.util.List;

@Service
public class ReviewService {
	private final ReviewPostgreDAO reviewPostgreDAO;

	@Autowired
	ReviewService(ReviewPostgreDAO reviewPostgreDAO) {
		this.reviewPostgreDAO = reviewPostgreDAO;
	}

	public Review createReview(int userId, int bookId, String text, int grade, int adminId) {
		Review review = new Review(userId, bookId, text, grade, adminId);
		reviewPostgreDAO.createReview(review);
		return review;
	}

	public List<Review> getReviewOfBook(int bookId) {
		return reviewPostgreDAO.getReviewsOfBook(bookId);
	}
}
