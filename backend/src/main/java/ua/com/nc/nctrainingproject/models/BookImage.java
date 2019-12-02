package ua.com.nc.nctrainingproject.models;

import java.io.File;
import java.io.InputStream;

public class BookImage extends Entity  {
    private int bookId;
    private InputStream image;

    public BookImage(){}

    public BookImage(int bookId, InputStream image) {
        this.bookId = bookId;
        this.image = image;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }
}
