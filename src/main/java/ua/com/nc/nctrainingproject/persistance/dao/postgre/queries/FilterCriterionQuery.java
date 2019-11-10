package ua.com.nc.nctrainingproject.persistance.dao.postgre.queries;


import java.sql.Date;
import java.util.*;

public class FilterCriterionQuery {

  private String genre;
  private String header;
  private String author;
  private String dateFrom;
  private String getDateTo;
  private List<String> notNullCriteries;

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

  public String getConditionsQuery() {
    return conditionsQuery;
  }

  public void setConditionsQuery(String conditionsQuery) {
    this.conditionsQuery = conditionsQuery;
  }

  Map<String, String> sqlCriteriaMap;
private String conditionsQuery;

  public Map<String, String> getSqlCriteriaMap() {
    return sqlCriteriaMap;
  }

  public void setSqlCriteriaMap(Map<String, String> sqlCriteriaMap) {
    this.sqlCriteriaMap = sqlCriteriaMap;
  }

  FilterCriterionQuery() {
    sqlCriteriaMap = new HashMap<>();
  }

  ArrayList <String> criterions = new ArrayList<>();
  public void makeMap(String genre, String header, String author){//--, String dateFrom, String dateTo) { sqlCriteriaMap.put("",genre);
    sqlCriteriaMap.put(BookQuery.CONDITIONS_NAME,header);
    criterions.add(header);
    sqlCriteriaMap.put(BookQuery.CONDITIONS_GENRES,genre);
    sqlCriteriaMap.put(BookQuery.CONDITION_AUTHOR,author);
   // sqlCriteriaMap.put(BookQuery.CONDITION_ANNOUNCEMENT_DATE,dateFrom+" "+dateTo);
    conditionsQuery = makeQuery();

  }
  private String makeQuery(){
    Set<String> keys = sqlCriteriaMap.keySet();
    String conditions = "";
    int i =0;
    for (String key:keys) {
      i++;
      conditions= conditions+key;
      if(keys.size() > i){
        conditionsQuery = conditionsQuery+" OR ";
      }
    }
    conditions = conditionsQuery + ";";
    return conditions;
  }
  public String[] delimetr(String s){
   return s.split(" ");
  }
}

