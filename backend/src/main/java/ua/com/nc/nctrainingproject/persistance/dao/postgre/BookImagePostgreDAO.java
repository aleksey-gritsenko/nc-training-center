package ua.com.nc.nctrainingproject.persistance.dao.postgre;

import com.sun.org.apache.xpath.internal.objects.XNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.nc.nctrainingproject.models.Book;
import ua.com.nc.nctrainingproject.models.BookFile;
import ua.com.nc.nctrainingproject.models.BookImage;
import ua.com.nc.nctrainingproject.persistance.dao.AbstractDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.BookFileManagementQuery;
import ua.com.nc.nctrainingproject.persistance.mappers.BookImageMapper;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class BookImagePostgreDAO extends AbstractDAO<BookImage> {

    @Autowired
    public BookImagePostgreDAO(DataSource dataSource) {
        super(dataSource);
    }

    public List<BookImage> getImages(){
        return super.getAllEntities(BookFileManagementQuery.GET_BOOK_IMAGES, new BookImageMapper());
    }

    public BookImage getBookImage(Book book){
        return super.getEntityById(BookFileManagementQuery.GET_BOOK_IMAGE, new BookImageMapper(), book.getId());
    }

    public BookImage deleteImage(BookImage bookImage){
        super.deleteEntityById(BookFileManagementQuery.DELETE_BOOK_IMAGE, bookImage.getBookId());
        return bookImage;
    }

    public void addImage(BookImage bookImage) {
        super.create(BookFileManagementQuery.INSERT_BOOK_IMAGE, new Object[]
                { bookImage.getBookId(), bookImage.getImage() });
    }

    public void updateImage(BookImage bookImage){
        super.update(BookFileManagementQuery.UPDATE_BOOK_IMAGE, new Object[]{
                bookImage.getBookId(), bookImage.getImage()});
    }
}
