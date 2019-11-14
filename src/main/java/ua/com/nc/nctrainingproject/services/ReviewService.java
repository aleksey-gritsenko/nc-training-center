package ua.com.nc.nctrainingproject.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.Review;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.ReviewPostgresDAO;

import java.util.Date;
import java.util.List;

@Service
public class ReviewService
{
  private final ReviewPostgresDAO reviewPostgresDAO;
  @Autowired
   ReviewService(ReviewPostgresDAO reviewPostgresDAO) { this.reviewPostgresDAO = reviewPostgresDAO; }

  public Review createReview(int userId, int bookId, String text, Date reviewDate, int grade, int adminId){
    Review review = new Review(userId,bookId,text,reviewDate,grade,adminId);
    reviewPostgresDAO.createReview(review);
    return review;
  }

  public List<Review> getReviewOfBook(int bookId){
    return reviewPostgresDAO.getReviewsOfBook(bookId);
  }

}
