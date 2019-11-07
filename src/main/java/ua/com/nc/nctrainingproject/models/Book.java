package ua.com.nc.nctrainingproject.models;

public class Book {
	private String title;
	private String header;
	private String author;
	private String overview;
	private String status;
	private int photoId;
	private int fileId;
	private int genre_id;

	public Book(String title, String header, String author, String overview,
				String status, int photoId, int fileId/*, int genre_id*/) {
		this.title = title;
		this.header = header;
		this.author = author;
		this.overview = overview;
		this.status = status;
		this.photoId = photoId;
		this.fileId = fileId;
		/*this.genre_id = genre_id;*/
	}

	public Book() {

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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

	public int getGenre_id() {
		return genre_id;
	}

	public void setGenre_id(int genre_id) {
		this.genre_id = genre_id;
	}

	@Override
	public String toString() {
		return "Book{" +
				"title='" + title + '\'' +
				", header='" + header + '\'' +
				", author='" + author + '\'' +
				", overview='" + overview + '\'' +
				", status='" + status + '\'' +
				", photoId=" + photoId +
				", fileId=" + fileId +
				/*", genre_id=" + genre_id +*/
				'}';
	}
}