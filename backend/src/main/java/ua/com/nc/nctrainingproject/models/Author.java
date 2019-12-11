package ua.com.nc.nctrainingproject.models;

public class Author extends Entity {

	private String name;

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

	@Override
	public String toString() {
		return "Author{" +
				"name='" + name + '\'' +
				'}';
	}
}
