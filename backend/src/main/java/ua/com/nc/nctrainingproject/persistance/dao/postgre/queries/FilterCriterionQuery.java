package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Stream;

@Component
public class FilterCriterionQuery {
	private ArrayList<String> genre;
	private String header;
	private ArrayList<String> author;


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

	String	q = BookQuery.GET_BOOKS_FILTRATION +
			makeAuthorConditions() + makeGenresConditions() + makeHeaderCondition();
		q=q.trim();
	  if(q.endsWith("OR")) {

		q=q.substring(0, q.length() - 2);	}
		return q;
	}
	public static String replaceLast(String text, String regex, String replacement) {
		return text.replaceFirst("(?s)"+regex+"(?!.*?"+regex+")", replacement);
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
		if(header.length() !=0 && !header.equals("")) {

			headers.add(header);
		}
		args = Stream.concat(Stream.concat(author.stream(), genre.stream()),
				headers.stream()).toArray();

		return args;
	}

	private String makeGenresConditions() {
		String genresCondition = " ";
		if (genre.size() != 0) {
			genresCondition =
					genre.stream().map((i) -> BookQuery.CONDITIONS_GENRES + " OR ")
							.reduce((g, n) -> g + n).get();
		}
		return genresCondition;
	}

	private String makeAuthorConditions() {
		String authorCondition = " ";

		if (author.size() != 0) {
			authorCondition = author.stream().map((i) -> BookQuery.CONDITION_AUTHOR
					+ " OR ").reduce((g, n) -> g + n).get();

		}
		System.out.println(authorCondition);
		return authorCondition;
	}

	private String makeHeaderCondition() {
		if(header != null && !header.equals("") && header.length() !=0){
			header = header+ "%";
		return BookQuery.CONDITIONS_NAME;}
		return " ";
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
}
