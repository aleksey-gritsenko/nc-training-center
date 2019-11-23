package ua.com.nc.nctrainingproject.models;

import java.util.Date;

public class Review extends Entity {
	private int userId;
	private int bookId;
	private String text;
	private Date reviewDate;
	private int grade;
	private int adminId;
	private boolean status;

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean getStatus() {
		return status;
	}

	public Review(int reviewId, int userId, int bookId, String text, Date reviewDate, int grade, int adminId) {
		super(reviewId);
		this.userId = userId;
		this.bookId = bookId;
		this.text = text;
		this.reviewDate = reviewDate;
		this.grade = grade;
		this.adminId = adminId;
	}

	public Review(int userId, int bookId, String text, Date reviewDate, int grade, int adminId) {
		this.userId = userId;
		this.bookId = bookId;
		this.text = text;
		this.reviewDate = reviewDate;
		this.grade = grade;
		this.adminId = adminId;
	}

	public Review(int userId, int bookId, String text, int grade, int adminId) {
		this.userId = userId;
		this.bookId = bookId;
		this.text = text;
		this.grade = grade;
		this.adminId = adminId;
	}

	public Review(int userId, int bookId, String text,  int grade, int adminId, boolean status) {
		this.userId = userId;
		this.bookId = bookId;
		this.text = text;
		this.grade = grade;
		this.adminId = adminId;
		this.status = status;
	}
	public Review(int reviewId, boolean status){
		super(reviewId);
		this.status = status;
	}


	public Review() {
	}

	public int getReviewId() {
		return super.getId();
	}

	public void setReviewId(int reviewId) {
		super.setId(reviewId);
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
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
}
