package ua.com.nc.nctrainingproject.models;

import java.io.File;
import java.io.InputStream;

public class BookFile extends Entity {
    private int bookId;
    private InputStream file;

    public BookFile(){}

    public BookFile(int bookId, InputStream file) {
        this.bookId = bookId;
        this.file = file;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public InputStream getFile() {
        return file;
    }

    public void setFile(InputStream file) {
        this.file = file;
    }
}
