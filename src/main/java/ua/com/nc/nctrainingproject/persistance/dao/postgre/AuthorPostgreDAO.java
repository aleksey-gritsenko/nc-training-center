package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.Author;
import ua.com.nc.nctrainingproject.persistance.dao.AbstractDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.AuthorQuery;
import ua.com.nc.nctrainingproject.persistance.mappers.AuthorMapper;

import java.util.List;

@Repository
public class AuthorPostgreDAO extends AbstractDAO<Author> {

  private final AuthorBookPostgreDAO authorBookPostgreDAO;

  @Autowired
  public AuthorPostgreDAO(JdbcTemplate jdbcTemplate, AuthorBookPostgreDAO authorBookPostgreDAO) {
    super(jdbcTemplate);
    this.authorBookPostgreDAO = authorBookPostgreDAO;
  }
/*
  public Author getAuthorById(int id) {
    Author author = super.getEntityById(AuthorQuery.GET_AUTHOR_BY_ID, new AuthorMapper(), id);
    author.setBooks(authorBookPostgreDAO.getBooksByAuthorId(id));
    return author;
  }
  public List<Author> getAllAuthors() {
    List<Author> authors = super.getAllEntities(AuthorQuery.GET_ALL_AUTHORS, new AuthorMapper());
    for (Author author : authors) {
      author.setBooks(authorBookPostgreDAO.getBooksByAuthorId(author.getId()));
    }
    return authors;
  }
*/
  public void deleteAuthorById(int id) {
    authorBookPostgreDAO.deleteAuthorsById(id);
    super.deleteEntityById(AuthorQuery.DELETE_BY_ID, id);
  }

//  public void updateAuthorById(int id, Author author) {
//    Object[] params = new Object[]{author.getName()};
//
//    super.updateEntityById(id, );
//
//  }


}
