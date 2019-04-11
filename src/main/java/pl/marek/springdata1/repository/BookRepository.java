package pl.marek.springdata1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.marek.springdata1.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
