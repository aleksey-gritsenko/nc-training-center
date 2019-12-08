package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.Book;
import ua.com.nc.nctrainingproject.models.BookFile;
import ua.com.nc.nctrainingproject.models.BookImage;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.BookFilePostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.BookImagePostgreDAO;
import java.util.List;

@Service
public class BookFileManagementService {
    private final BookImagePostgreDAO bookImagePostgreDAO;
    private final BookFilePostgreDAO bookFilePostgreDAO;

    @Autowired
    public BookFileManagementService(BookImagePostgreDAO bookImagePostgreDAO, BookFilePostgreDAO bookFilePostgreDAO){
        this.bookImagePostgreDAO = bookImagePostgreDAO;
        this.bookFilePostgreDAO = bookFilePostgreDAO;
    }

    // IMAGES
    public List<BookImage> getImages(){
        return bookImagePostgreDAO.getImages();
    }

    public BookImage getBookImage(Book book){
        return bookImagePostgreDAO.getBookImage(book);
    }

    public BookImage deleteImage(BookImage bookImage){
        bookImagePostgreDAO.deleteImage(bookImage);
        return bookImage;
    }

    public BookImage addImage(BookImage bookImage) {
        bookImagePostgreDAO.addImage(bookImage);
        return bookImage;
    }

    public BookImage updateImage(BookImage bookImage){
        bookImagePostgreDAO.updateImage(bookImage);
        return bookImage;
    }

    // FILES
    public List<BookFile> getFiles(){
        return bookFilePostgreDAO.getFiles();
    }

    public BookFile getBookFile(Book book){
        return bookFilePostgreDAO.getBookFile(book);
    }

    public BookFile deleteFile(BookFile bookFile){
        bookFilePostgreDAO.deleteFile(bookFile);
        return bookFile;
    }

    public BookFile addFile(BookFile bookFile) {
        bookFilePostgreDAO.addFile(bookFile);
        return bookFile;
    }

    public BookFile updateFile(BookFile bookFile){
        bookFilePostgreDAO.updateFile(bookFile);
        return bookFile;
    }
}
