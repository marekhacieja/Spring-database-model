package pl.marek.springdata1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.marek.springdata1.exception.RentException;
import pl.marek.springdata1.model.Book;
import pl.marek.springdata1.model.Customer;
import pl.marek.springdata1.repository.BookRepository;
import pl.marek.springdata1.repository.CustomerRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Scanner;

@Service
public class RentService {
    private Scanner scanner;
    private BookRepository bookRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public RentService(Scanner scanner, BookRepository bookRepository, CustomerRepository customerRepository) {
        this.scanner = scanner;
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public void rentBookToCustomer() {
        try {
            rent();
        } catch(RentException e) {
            System.err.println(e.getMessage());
        }
    }

    private void rent() {
        System.out.println("Enter customer PESEL:");
        String customerPesel = scanner.nextLine();
        System.out.println("Enter Book id:");
        long bookId = scanner.nextLong();
        Optional<Customer> customer = customerRepository.findByPesel(customerPesel);
        Optional<Book> book = bookRepository.findById(bookId);
        if(customer.isPresent())
            book.ifPresentOrElse(b -> {
                if(b.getQuantity() > b.getCustomers().size())
                    b.addCustomer(customer.get());
                else
                    throw new RentException("There are no books available with the Id");
            }, () -> {
                throw new RentException("There are no books with the indicated ID");
            });
        else
            throw new RentException("Wrong customer's ID");
    }

}
