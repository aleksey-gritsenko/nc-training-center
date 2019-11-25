package ua.com.nc.nctrainingproject.models;

import java.util.List;

public class Book extends Entity {

    private String header;
    private List<Author> authors;
    private String overview;
    private String status;
    private int photo;
    private int fileId;
    private String genre;

    public Book(String header, List<Author> authors, String overview,
                String status, int photo, int fileId, String genre) {
        this.header = header;
        this.authors = authors;
        this.overview = overview;
        this.status = status;
        this.photo = photo;
        this.fileId = fileId;
        this.genre = genre;
    }

    public Book(String header, String overview, String status, int photo, int fileId) {
        this.header = header;
        this.overview = overview;
        this.status = status;
        this.photo = photo;
        this.fileId = fileId;
    }

    public Book(int id, String header, String overview, String status, int photo, int fileId, String genre) {
        super(id);
        this.header = header;
        this.overview = overview;
        this.status = status;
        this.photo = photo;
        this.fileId = fileId;
        this.genre = genre;
    }

    public Book(String header, String overview, String status, int photo, int fileId, String genre, List<Author> authors) {
        this.header = header;
        this.overview = overview;
        this.status = status;
        this.photo = photo;
        this.fileId = fileId;
        this.genre = genre;
        this.authors = authors;
    }


    public Book() {

    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPhotoId() {
        return photo;
    }

    public void setPhotoId(int photo) {
        this.photo = photo;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "header='" + header + '\'' +
                ", authors=" + authors +
                ", overview='" + overview + '\'' +
                ", status='" + status + '\'' +
                ", photo='" + photo + '\'' +
                ", fileId=" + fileId +
                ", genre='" + genre + '\'' +
                '}';
    }
}
