package ua.com.nc.nctrainingproject.models;

import java.util.List;

public class Book extends Entity {
  private String header;
  private List<Author> authors;
  private String overview;
  private String status;
  private int photoId;
  private int fileId;
  private String genre;

  public Book(String header, List<Author> authors, String overview, String status, int photoId, int fileId, String genre) {
    this.header = header;
    this.authors = authors;
    this.overview = overview;
    this.status = status;
    this.photoId = photoId;
    this.fileId = fileId;
    this.genre = genre;
  }

  public Book(int id, String header, List<Author> authors, String overview, String status, int photoId, int fileId, String genre) {
    super(id);
    this.header = header;
    this.authors = authors;
    this.overview = overview;
    this.status = status;
    this.photoId = photoId;
    this.fileId = fileId;
    this.genre = genre;
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

  public int getPhotoId() {
    return photoId;
  }

  public void setPhotoId(int photoId) {
    this.photoId = photoId;
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


}
