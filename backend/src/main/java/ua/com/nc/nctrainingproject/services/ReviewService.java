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

	@Autowired
	ReviewService(ReviewPostgreDAO reviewPostgreDAO) {
		this.reviewPostgreDAO = reviewPostgreDAO;
	}

	public Review createReview(Review review)  {
		if(review!=null){
			review.setReviewDate(new Date());
			reviewPostgreDAO.createReview(review);
			return review;
		}
		return null;
	}

	public List<Review> getReviewOfBook(int bookId) {
		return reviewPostgreDAO.getReviewsOfBook(bookId);
	}

	public void acceptReview(Review review){
		if(review!=null) {
			review.setStatus(true);
			reviewPostgreDAO.acceptReview(review);
		}
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
