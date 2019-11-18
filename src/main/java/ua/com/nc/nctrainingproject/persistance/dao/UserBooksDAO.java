package ua.com.nc.nctrainingproject.persistance.dao;

import ua.com.nc.nctrainingproject.models.Book;

import java.util.List;

public interface UserBooksDAO {

  void addBookToUser(int userId, int bookId);

  List<Book> getAllUserBooks(int userId);

  List<Book> getAllFavouriteBooks(int userId);

  List<Book> getAllReadBooks(int userId);

  void markBookAsRead(int userId, int bookId);

  void markBookAsFavourite(int userId, int bookId);

  void removeFromRead(int userId, int bookId);

  void removeFromFavourite(int userId, int bookId);


}
