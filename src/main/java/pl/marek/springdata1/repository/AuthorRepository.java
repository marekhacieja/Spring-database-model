package pl.marek.springdata1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.marek.springdata1.model.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByLastNameIgnoreCase(String lastName);
}
