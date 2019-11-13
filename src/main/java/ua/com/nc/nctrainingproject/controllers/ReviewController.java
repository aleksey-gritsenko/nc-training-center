package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.models.Review;
import ua.com.nc.nctrainingproject.services.ReviewService;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class ReviewController {
  private final ReviewService reviewService;

  @Autowired
  public ReviewController(ReviewService reviewService) {
    this.reviewService = reviewService;
  }

  @RequestMapping(value = "/review")
  public Review addReview(@RequestParam(name = "book") int bookId,
                          @RequestParam(name = "user") int userId,
                          @RequestParam(name = "text") String text,
                          @RequestParam(name = "reviewDate") Date reviewDate,
                          @RequestParam(name = "grade") int grade,
                          @RequestParam(name = "adminId") int adminId){
    return reviewService.createReview(bookId, userId, text, reviewDate, grade, adminId);
  }

  @RequestMapping(value="/reviews", method = RequestMethod.GET)
  public List<Review> getReviewsOfBook(@RequestParam(name = "book") int bookId){
    return reviewService.getReviewOfBook(bookId);
  }
}


