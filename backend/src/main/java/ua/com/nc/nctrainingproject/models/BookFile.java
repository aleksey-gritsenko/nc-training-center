package ua.com.nc.nctrainingproject.models;

import java.io.File;
import java.io.InputStream;

public class BookFile extends Entity {
    private int bookId;
    private byte[] file;

    public BookFile(){}

    public BookFile(int bookId, byte[] file) {
        this.bookId = bookId;
        this.file = file;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
