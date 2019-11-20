package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Stream;

@Component
public class FilterCriterionQuery {
	private ArrayList<String> genre;
	private String header;
	private ArrayList<String> author;
	private String dateFrom;
	private String getDateTo;

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	private Object[] args;

	public FilterCriterionQuery() {
		genre = new ArrayList<>();
		author = new ArrayList<>();
	}

	public String makeQuery() {
		return BookQuery.GET_BOOKS_FILTRATION +
				makeAuthorConditins() + makeGenresConditions() + makeHeaderCondition();
	}

	public Object[] makeArrayArgs() {
		ArrayList<Object> argsList = new ArrayList<>();
		ArrayList<String> headers = new ArrayList<>();
		headers.add(header);

		argsList.addAll(author);
		argsList.addAll(genre);
		argsList.addAll(headers);

		args = argsList.toArray();
		return args;
	}

	public Object[] makeArrayArgsStream() {
		ArrayList<String> headers = new ArrayList<>();
		headers.add(header);
		args = Stream.concat(Stream.concat(genre.stream(), author.stream()),
				headers.stream()).toArray();

		return args;
	}

	private StringBuilder makeGenresConditions() {
		StringBuilder genresCondition = new StringBuilder("");

		if (genre.size() != 0) {
			for (int i = 0; i < genre.size(); i++) {
				genresCondition.append(BookQuery.CONDITIONS_GENRES);
				if (i < genre.size()) {
					genresCondition.append(" OR ");
				}
			}
		}
		return genresCondition;
	}

	private StringBuilder makeAuthorConditins() {
		StringBuilder authorCondition = new StringBuilder("");

		if (author.size() != 0) {
			for (int i = 0; i < author.size(); i++) {
				authorCondition.append(BookQuery.CONDITION_AUTHOR);
				authorCondition.append(" OR ");
			}
		}
		return authorCondition;
	}

	private StringBuilder makeHeaderCondition() {
		return new StringBuilder(BookQuery.CONDITIONS_NAME);
	}

	public ArrayList<String> getGenre() {
		return genre;
	}

	public void setGenre(ArrayList<String> genre) {
		this.genre = genre;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public ArrayList<String> getAuthor() {
		return author;
	}

	public void setAuthor(ArrayList<String> author) {
		this.author = author;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getGetDateTo() {
		return getDateTo;
	}

	public void setGetDateTo(String getDateTo) {
		this.getDateTo = getDateTo;
	}
}
