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

	public Review createReview(int userId, int bookId, String text, int grade) {
		Review review = new Review(userId, bookId, text, grade);
		reviewPostgreDAO.createReview(review);
		return review;
	}

	public List<Review> getReviewOfBook(int bookId) {
		return reviewPostgreDAO.getReviewsOfBook(bookId);
	}

	public void acceptReview(int reviewId,int adminId, boolean status){
		Review review = new Review(reviewId, adminId, status);
		reviewPostgreDAO.acceptReview(review);
	}

	public List<Review> getAcceptedReview(boolean status, int bookId){
		return reviewPostgreDAO.getAcceptedReviewsOfBook(status, bookId);
	}

	public void deleteReviewById(int reviewId){
		if (reviewPostgreDAO.getReviewById(reviewId)!=null) {
			reviewPostgreDAO.deleteReviewById(reviewId);
		}
	}

	public Review getReviewById(int reviewId){
		if (reviewPostgreDAO.getReviewById(reviewId)!=null) {
			return reviewPostgreDAO.getReviewById(reviewId);
		}
		return null;
	}
}
