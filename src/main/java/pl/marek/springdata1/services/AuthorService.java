package pl.marek.springdata1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.marek.springdata1.exception.AuthorNotFoundException;
import pl.marek.springdata1.model.Author;
import pl.marek.springdata1.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class AuthorService {
    private Scanner scanner;
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(Scanner scanner, AuthorRepository authorRepository) {
        this.scanner = scanner;
        this.authorRepository = authorRepository;
    }

    public void createAuthor() {
        Author author = readAuthor();
        authorRepository.save(author);
        System.out.println("Added author");
        System.out.println(author);
    }

    private Author readAuthor() {
        Author author = new Author();
        System.out.println("Author first name:");
        author.setFirstName(scanner.nextLine());
        System.out.println("Author last name:");
        author.setLastName(scanner.nextLine());
        return author;
    }

    public void removeAuthor() {
        System.out.println("Enter the author ID:");
        long categoryId = scanner.nextLong();
        Optional<Author> author = authorRepository.findById(categoryId);
        author.ifPresentOrElse(authorRepository::delete, ()-> System.out.println("Wrong author's ID"));
    }

    public void showAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        if (authors != null) {
            authors.forEach(System.out::println);
        } else System.out.println("The list is empty.");
    }
}

