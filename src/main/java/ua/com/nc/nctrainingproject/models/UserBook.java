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

  public UserBook() {
  }


  public int getUserId() {
    return userId;
  }

  public int getBookId() {
    return bookId;
  }

  public boolean isRead() {
    return isRead;
  }

  public boolean isFavorite() {
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
}
