package ua.com.nc.nctrainingproject.models;

import java.util.List;

public class Author extends Entity {

	private String name;
	private List<Book> books;

	public Author(int id, String name) {
		super(id);
		this.name = name;
	}

	public Author(String name) {
		this.name = name;
	}

	public Author() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Author{" +
				"name='" + name + '\'' +
				", books=" + books +
				'}';
	}
}
