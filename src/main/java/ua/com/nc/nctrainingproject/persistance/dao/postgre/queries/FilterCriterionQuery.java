package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;

@Component

public class FilterCriterionQuery {

  private ArrayList<String> genre;
  private String header;
  private ArrayList<String>author;
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
  public String makeQuery(){
    return BookQuery.JOIN_BOOKS + makeHeaderCondition()+
      makeAuthorConditins()+makeGenresConditins();
  }
public Object[] makeArrayArgs(){
  List<Object> argsList = new ArrayList<Object>();
  List<String> headers = new ArrayList<>();
  headers.add(header);
  argsList.addAll(0,headers);
  argsList.addAll(headers.size()-1,author);
  argsList.addAll(author.size()-1,genre);

  //  argsList.addAll(argsList.size()-1,filterCriterionQuery.getAuthor());
  // argsList.add(filterCriterionQuery.getHeader());

   args = argsList.toArray();
   return args;
}
  private String makeGenresConditins(){
    String genresCondition="";
    if(genre.size()==0){
      return "";
    }
    for (int i =0;i <genre.size();i++){
      genresCondition =genresCondition +BookQuery.CONDITIONS_GENRES;
      if(i < genre.size()-1){
        genresCondition = genresCondition + " OR ";
      }
    }
    return genresCondition;
  }
  private String makeAuthorConditins(){
    String authorCondition="";
    if(author.size()==0){
      return "";
    }
    for (int i =0;i <author.size();i++){
      authorCondition =authorCondition +BookQuery.CONDITION_AUTHOR;
      if(i<author.size()){
        authorCondition = authorCondition + " OR ";
      }
    }
    return authorCondition;
  }
  private String makeHeaderCondition(){
    return BookQuery.CONDITIONS_NAME + " OR ";
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

/* private List<String> notNullCriteries;
  public FilterCriterionQuery(String genre, String header, String author, String dateFrom, String getDateTo) {
    notNullCriteries = new ArrayList<>();
    this.genre = genre;
    this.header = header;
    this.author = author;
    this.dateFrom = dateFrom;
    this.getDateTo = getDateTo;
    notNullCriteries.add(genre);
    notNullCriteries.add(header);
    notNullCriteries.add(author);

  }

  public FilterCriterionQuery(String genre, String header, String author, Date dateFrom) {
    notNullCriteries = new ArrayList<>();

    this.genre = genre;
    this.header = header;
    this.author = author;
    // this.dateFrom = dateFrom;
    notNullCriteries.add(genre);
    notNullCriteries.add(header);
    notNullCriteries.add(author);
  }

  public FilterCriterionQuery(String genre, String header, String author) {
    notNullCriteries = new ArrayList<>();

    this.genre = genre;
    this.header = header;
    this.author = author;
    notNullCriteries.add(genre);
    notNullCriteries.add(header);
    notNullCriteries.add(author);
  }

  public FilterCriterionQuery(String genre, String header) {
    notNullCriteries = new ArrayList<>();
    this.genre = genre;
    this.header = header;
    notNullCriteries.add(genre);
    notNullCriteries.add(header);
  }

  public FilterCriterionQuery(String genre) {
    notNullCriteries = new ArrayList<>();
    this.genre = genre;
    notNullCriteries.add(genre);
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public void setDateFrom(String dateFrom) {
    this.dateFrom = dateFrom;
  }

  public void setGetDateTo(String getDateTo) {
    this.getDateTo = getDateTo;
  }

  public void setNotNullCriteries(List<String> notNullCriteries) {
    this.notNullCriteries = notNullCriteries;
  }




   /*
  Map<String, String> sqlCriteriaMap;
private String conditionsQuery;

  public String getConditionsQuery() {
    return conditionsQuery;
  }

  public void setConditionsQuery(String conditionsQuery) {
    this.conditionsQuery = conditionsQuery;
  }

  public Map<String, String> getSqlCriteriaMap() {
    return sqlCriteriaMap;
  }

  public void setSqlCriteriaMap(Map<String, String> sqlCriteriaMap) {
    this.sqlCriteriaMap = sqlCriteriaMap;
  }

  FilterCriterionQuery() {
    sqlCriteriaMap = new HashMap<>();
  }


  ArrayList<String> criterions = new ArrayList<>();

  public void makeMap(String genre, String header, String author) {//--, String dateFrom, String dateTo) { sqlCriteriaMap.put("",genre);
    if (header != null) {
      sqlCriteriaMap.put(BookQuery.CONDITIONS_NAME, header);
    }
    if (genre != null) {
      sqlCriteriaMap.put(BookQuery.CONDITIONS_GENRES, genre);
    }
    if (author != null) {
      sqlCriteriaMap.put(BookQuery.CONDITION_AUTHOR, author);
    }
    // sqlCriteriaMap.put(BookQuery.CONDITION_ANNOUNCEMENT_DATE,dateFrom+" "+dateTo);
    conditionsQuery = makeQuery();

  }
  private String makeQuery(){
    Set<String> keys = sqlCriteriaMap.keySet();
    String conditions = "";
    int i = 0;
    for (String key:keys) {
      i++;
      conditions= conditions+key;
      if(keys.size() > i){
        conditions = conditions+" OR ";
      }
    }
    conditions = conditions + ";";
    return conditions;
  }
  public String[] delimetr(String s){
   return s.split(" ");
  }

    */
}

