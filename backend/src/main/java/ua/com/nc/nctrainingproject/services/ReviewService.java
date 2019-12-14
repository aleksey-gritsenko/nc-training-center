package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.Review;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.ReviewPostgreDAO;

import java.util.Date;
import java.util.List;

@Service
public class ReviewService {
	private final ReviewPostgreDAO reviewPostgreDAO;
	private final ActionService actionService;

	@Autowired
	ReviewService(ReviewPostgreDAO reviewPostgreDAO, ActionService actionService) {
		this.reviewPostgreDAO = reviewPostgreDAO;
		this.actionService = actionService;
	}

	public Review createReview(Review review) {
		review.setReviewDate(new Date());
		reviewPostgreDAO.createReview(review);
		review.setReviewId(getAllReview().get(getAllReview().size() - 1).getId());
		actionService.addNewAction(review.getUserId(), 6);
		return review;
	}

	public List<Review> getReviewOfBook(int bookId) {
		return reviewPostgreDAO.getReviewsOfBook(bookId);
	}

	public void acceptReview(Review review) {
		if (getReviewById(review.getReviewId()) != null) {
			review.setStatus(true);
			reviewPostgreDAO.acceptReview(review);
		}
	}

	public List<Review> getAcceptedReview(int bookId) {
		return reviewPostgreDAO.getAcceptedReviewsOfBook(true, bookId);
	}

	public List<Review> getNotAcceptedReview(int bookId) {
		return reviewPostgreDAO.getAcceptedReviewsOfBook(false, bookId);
	}


	public void deleteReviewById(int reviewId) {
		if (reviewPostgreDAO.getReviewById(reviewId) != null) {
			reviewPostgreDAO.deleteReviewById(reviewId);
		}
	}

	public Review getReviewById(int reviewId) {
		if (reviewPostgreDAO.getReviewById(reviewId) != null) {
			return reviewPostgreDAO.getReviewById(reviewId);
		}
		return null;
	}

	public List<Review> getAllReview() {
		return reviewPostgreDAO.getAllReview();
	}
}
