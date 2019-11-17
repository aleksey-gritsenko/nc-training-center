package ua.com.nc.nctrainingproject.persistance.dao;

import ua.com.nc.nctrainingproject.models.Review;

import java.util.List;

public interface ReviewDAO {
  List<Review>  getReviewsOfBook(int book_id);
  void createReview(Review review);
}


