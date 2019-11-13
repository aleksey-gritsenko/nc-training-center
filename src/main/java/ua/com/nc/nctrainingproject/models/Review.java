package ua.com.nc.nctrainingproject.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Review {
  private int userId;
  private int bookId;
  private String text;
  private Date reviewDate;
  private int grade;
  private int adminId;

  public void setUserId(int userId) {
    this.userId = userId;
  }

  @Override
  public String toString() {
    return "Review{" +
      "userId=" + userId +
      ", bookId=" + bookId +
      ", text='" + text + '\'' +
      ", reviewDate=" + reviewDate +
      ", grade=" + grade +
      ", adminId=" + adminId +
      '}';
  }

  public void setBookId(int bookId) {
    this.bookId = bookId;
  }

  public void setText(String text) {
    this.text = text;
  }

  public void setReviewDate(Date reviewDate) {
    this.reviewDate = reviewDate;
  }

  public void setGrade(int grade) {
    this.grade = grade;
  }

  public void setAdminId(int adminId) {
    this.adminId = adminId;
  }

  public Review(){
  }

  public Review(int userId, int bookId, String text, Date reviewDate, int grade, int adminId){
    this.userId = userId;
    this.bookId = bookId;
    this.text = text;
    this.reviewDate = reviewDate;
    this.grade = grade;
    this.adminId=adminId;
  }

  public int getBookId() {
    return bookId;
  }

  public String getText() {
    return text;
  }

  public Date getReviewDate() {
    return reviewDate;
  }

  public int getGrade() {
    return grade;
  }

  public int getAdminId() {
    return adminId;
  }
  public int getUserId() {
    return userId;
  }

}
