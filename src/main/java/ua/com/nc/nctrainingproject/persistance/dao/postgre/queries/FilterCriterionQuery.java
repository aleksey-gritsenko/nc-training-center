package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;


import org.springframework.stereotype.Component;

import java.util.*;

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
    return BookQuery.GET_BOOKS +
      makeAuthorConditins() + makeGenresConditins() + makeHeaderCondition();
  }

  public Object[] makeArrayArgs() {
    ArrayList<Object> argsList = new ArrayList<Object>();
    ArrayList<String> headers = new ArrayList<>();
    headers.add(header);
    // headers.add("harry%");

    argsList.addAll(author);
    argsList.addAll(genre);
    argsList.addAll(headers);


    args = argsList.toArray();
    return args;
  }

  private String makeGenresConditins() {
    String genresCondition = "";
    if (genre.size() == 0) {
      return "";
    }
    for (int i = 0; i < genre.size(); i++) {
      genresCondition = genresCondition + BookQuery.CONDITIONS_GENRES;
      if (i < genre.size()) {
        genresCondition = genresCondition + " OR ";
      }
    }
    return genresCondition;
  }

  private String makeAuthorConditins() {
    String authorCondition = "";
    if (author.size() == 0) {
      return "";
    }
    for (int i = 0; i < author.size(); i++) {
      authorCondition = authorCondition + BookQuery.CONDITION_AUTHOR;
      if (i < author.size()) {
        authorCondition = authorCondition + " OR ";
      }
    }
    return authorCondition;
  }

  private String makeHeaderCondition() {
    return BookQuery.CONDITIONS_NAME;
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
