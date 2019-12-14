package ua.com.nc.nctrainingproject.models;

import java.util.List;

public class Genre extends Entity {
	private String name;
	private List<Book> books;

	public Genre(int id, String name) {
		super(id);
		this.name = name;
	}

	public Genre() {
	}

	@Override
	public String toString() {
		return "Genre{" +
				"name='" + name + '\'' +
				", books=" + books +
				'}';
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
