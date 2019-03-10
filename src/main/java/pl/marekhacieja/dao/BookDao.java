package pl.marekhacieja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import pl.marekhacieja.model.Book;

@Repository
@Transactional
public class BookDao extends GenericDao<Book, Long> {
	
	@PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public List<Book> getByAuthor(String author) {
        TypedQuery<Book> query = 
                entityManager.createNamedQuery("Books.findByAuthor", Book.class);
        query.setParameter("author", author);
        List<Book> resultList = query.getResultList();
        return resultList;
    }
    
    public void deleteByTitle(String title) {
        Query query = 
                entityManager.createQuery("DELETE FROM Book p WHERE p.title = :title");
        query.setParameter("title", title);
        query.executeUpdate();
    }
    
    public List<Book> getAll() {
        TypedQuery<Book> getAllQuery = 
                entityManager.createNamedQuery("Books.findAll", Book.class);
        List<Book> resultList = getAllQuery.getResultList();
        return resultList;
    }
    
    public void deleteAll() {
        Query deleteAllQuery = entityManager.createNamedQuery("Books.deleteAll");
        deleteAllQuery.executeUpdate();
    }
	
}
