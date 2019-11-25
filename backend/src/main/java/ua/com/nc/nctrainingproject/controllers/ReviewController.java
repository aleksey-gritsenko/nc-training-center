package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.Review;
import ua.com.nc.nctrainingproject.services.ReviewService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/review")
public class ReviewController {
	private final ReviewService reviewService;

	@Autowired
	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	@RequestMapping(method = RequestMethod.POST)
	public Review addReview(@RequestParam(name = "book") int bookId,
							@RequestParam(name = "user") int userId,
							@RequestParam(name = "text") String text,
							@RequestParam(name = "grade") int grade) {
		return reviewService.createReview(bookId, userId, text, grade);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<Review> getReviewsOfBook(@RequestParam(name = "book") int bookId) {
		System.out.println(bookId);
		return reviewService.getReviewOfBook(bookId);
	}

	@RequestMapping(value = "/accepted", method = RequestMethod.GET)
	public List<Review> getAcceptedReviews(@RequestParam(name = "book") int bookId,
										 @RequestParam(name = "status") boolean status) {
		return reviewService.getAcceptedReview(status,bookId);
	}


	@RequestMapping(value = "/accept")
	public void acceptReview(@RequestParam(name = "review") int reviewId,
							 @RequestParam(name = "status") boolean status,
							 @RequestParam(name = "admin") int adminId) {
		reviewService.acceptReview(reviewId,adminId,status);
	}

	@RequestMapping(value = "/delete")
	public void deleteReviewById(@RequestParam(name="review") int reviewId){
		reviewService.deleteReviewById(reviewId);
	}

}


