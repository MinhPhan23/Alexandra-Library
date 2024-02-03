package pkg3350;
import java.sql.PreparedStatement;

/**
 *
 * @author aasho
 */
public interface BookPersistent {

    int upload(Book book);

    int upload(Booklist list);

    void delete(Book book);

    void delete(Booklist list);

    Book update(Book book);

    Book getBook(Book book);

    Booklist getBooklist(Booklist list);

    Booklist getAll();

    Booklist getSQL(PreparedStatement statement);
}