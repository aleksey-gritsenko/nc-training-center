package ua.com.nc.nctrainingproject.models;

public class UserBook {
	private int userId;
	private int bookId;
	private boolean isRead;
	private boolean isFavorite;

	public UserBook(int userId, int bookId, boolean isRead, boolean isFavorite) {
		this.userId = userId;
		this.bookId = bookId;
		this.isRead = isRead;
		this.isFavorite = isFavorite;
	}
	public UserBook(int userId, int bookId){
		this.userId = userId;
		this.bookId = bookId;
		this.isRead = false;
		this.isFavorite = false;
	}


	public UserBook() {
	}

	public int getUserId() {
		return userId;
	}

	public int getBookId() {
		return bookId;
	}

	public boolean getRead() {
		return isRead;
	}

	public boolean getFavorite() {
		return isFavorite;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public void setRead(boolean read) {
		isRead = read;
	}

	public void setFavorite(boolean favorite) {
		isFavorite = favorite;
	}

	@Override
	public String toString() {
		return "UserBook{" +
				"userId=" + userId +
				", bookId=" + bookId +
				", isRead=" + isRead +
				", isFavorite=" + isFavorite +
				'}';
	}
}
