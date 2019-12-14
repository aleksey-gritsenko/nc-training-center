package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component
public class FilterCriterionQuery {
    private List<String> genre;
    private String header;
    private List<String> author;
    private Object[] args;
    private static final String CONDITION_OR = " OR ";
    private static final String CONDITION_AND = " AND ";
    private static final int FROM = 0;
    private static final String PERCENT = "%";
    public FilterCriterionQuery() {
        genre = new ArrayList<>();
        author = new ArrayList<>();
    }


    public String makeQuery() {

        String queryWithConditions = BookQuery.GET_BOOKS_FILTRATION +
                makeConditions(author, BookQuery.CONDITION_AUTHOR) + makeConditions(genre, BookQuery.CONDITIONS_GENRES) + makeHeaderCondition();

        return checkStr(queryWithConditions);
    }

    public Object[] makeArrayArgsStream() {
        ArrayList<String> headers = new ArrayList<>();
        if (header.length() != 0) {

            headers.add(header);
        }
        args = Stream.concat(Stream.concat(author.stream(), genre.stream()),
                headers.stream()).toArray();

        return args;
    }

    private String checkStr(String string) {
        string = string.trim();

        if (string.endsWith(CONDITION_OR.trim())) {
            string = string.substring(FROM, string.length() - CONDITION_OR.trim().length());
        }
        if (string.endsWith(CONDITION_AND.trim())) {
            string = string.substring(FROM, string.length() - CONDITION_AND.trim().length());
        }
        return string;

    }


    private String makeConditions(List<String> arg, String condition) {
        String conditionString = " ";
        if (arg.size() != 0) {
            conditionString =
                    arg.stream().map((i) -> condition + CONDITION_OR)
                            .reduce((wholeString, newCondition) -> wholeString + newCondition).get();
            conditionString = checkStr(conditionString);
            conditionString = conditionString + CONDITION_AND;
        }

        return conditionString;
    }


    private String makeHeaderCondition() {
        if (!header.trim().isEmpty()) {

            header = PERCENT + header + PERCENT;
            return BookQuery.CONDITIONS_NAME;
        }
        return " ";
    }

    public List<String> getGenre() {
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

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(ArrayList<String> author) {
        this.author = author;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
