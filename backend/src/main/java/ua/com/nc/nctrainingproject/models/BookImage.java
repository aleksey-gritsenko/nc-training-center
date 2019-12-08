package ua.com.nc.nctrainingproject.models;

import java.io.File;
import java.io.InputStream;

public class BookImage extends Entity  {
    private int bookId;
    private byte[] image;

    public BookImage(){}

    public BookImage(int bookId, byte[] image) {
        this.bookId = bookId;
        this.image = image;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
