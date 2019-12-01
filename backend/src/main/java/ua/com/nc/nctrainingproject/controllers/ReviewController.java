package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<?> addReview(@RequestBody  Review review) {
		Review response =  reviewService.createReview(review);
		return response != null ? ResponseEntity.ok(response) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/detail")
	public ResponseEntity<?> getReviewById(@RequestParam(name = "review") int reviewId){
		Review response = reviewService.getReviewById(reviewId);
		return response != null ? ResponseEntity.ok(response) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<?>  getReviewsOfBook(@RequestParam(name = "book") int bookId) {
		return ResponseEntity.ok(reviewService.getReviewOfBook(bookId));
	}

	@RequestMapping(value = "/accepted", method = RequestMethod.GET)
	public ResponseEntity<?>  getAcceptedReviews(@RequestParam(name = "book") int bookId,
										 @RequestParam(name = "status") boolean status) {
		return ResponseEntity.ok(reviewService.getAcceptedReview(status,bookId));
	}


	@RequestMapping(value = "/accept")
	public void acceptReview(@RequestBody Review review) {
		reviewService.acceptReview(review);
	}

	@RequestMapping(value = "/delete")
	public void deleteReviewById(@RequestParam(name="review") int reviewId){
		reviewService.deleteReviewById(reviewId);
	}

}


