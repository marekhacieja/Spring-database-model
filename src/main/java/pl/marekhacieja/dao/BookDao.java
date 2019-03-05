package pl.marekhacieja.dao;

import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import pl.marekhacieja.model.Book;

@Repository
@Transactional
public class BookDao extends GenericDao<Book, Long> {
}
