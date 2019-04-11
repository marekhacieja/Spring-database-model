package pl.marek.springdata1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.marek.springdata1.exception.AuthorNotFoundException;
import pl.marek.springdata1.model.Author;
import pl.marek.springdata1.model.Book;
import pl.marek.springdata1.repository.AuthorRepository;
import pl.marek.springdata1.repository.BookRepository;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class BookService {
    private Scanner scanner;
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    @Autowired
    public BookService(Scanner scanner, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.scanner = scanner;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Transactional
    public void createBook() {
        try {
            Book book= readBook();
            bookRepository.save(book);
            System.out.println("Added book");
            System.out.println(book);
        } catch (AuthorNotFoundException e) {
            System.err.println( "the book could not be added: " + e.getMessage());
        }
    }

    private Book readBook() {
        Book book = new Book();
        System.out.println("Book's name:");
        book.setTitle(scanner.nextLine());
        System.out.println("Book's description:");
        book.setDescription(scanner.nextLine());
        System.out.println("Price:");
        book.setPrice(scanner.nextDouble());
        System.out.println("Quantity:");
        book.setQuantity(scanner.nextInt());
        scanner.nextLine();
        System.out.println("Book's author last name");
        String authorLastName = scanner.nextLine();
        Optional<Author> author = authorRepository.findByLastNameIgnoreCase(authorLastName);
        author.ifPresentOrElse(book::setAuthor, () -> {
                    throw new AuthorNotFoundException("Wrong author's last name!");
                });
        return book;
    }
    public void removeBook() {
        System.out.println("enter the book ID");
        long bookId = scanner.nextLong();
        Optional<Book> book = bookRepository.findById(bookId);
        book.ifPresentOrElse(bookRepository::delete, () -> System.out.println("There are no books with the indicated ID"));
    }

    public void showAllBooks() {
        List<Book> books = bookRepository.findAll();
        if(books!=null){
            books.forEach(System.out::println);
        }else System.out.println("The list is empty.");
    }
}
