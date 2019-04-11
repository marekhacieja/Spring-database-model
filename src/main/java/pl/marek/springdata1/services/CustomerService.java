package pl.marek.springdata1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.marek.springdata1.model.Book;
import pl.marek.springdata1.model.Customer;
import pl.marek.springdata1.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CustomerService {
    private Scanner scanner;
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(Scanner scanner, CustomerRepository customerRepository) {
        this.scanner = scanner;
        this.customerRepository = customerRepository;
    }

    public void createCustomer() {
        Customer customer = readCustomer();
        customerRepository.save(customer);
        System.out.println("Added client");
        System.out.println(customer);
    }

    private Customer readCustomer() {
        Customer customer = new Customer();
        System.out.println("First name:");
        customer.setFirstName(scanner.nextLine());
        System.out.println("Last name:");
        customer.setLastName(scanner.nextLine());
        System.out.println("Pesel:");
        customer.setPesel(scanner.nextLine());
        return customer;
    }

    public void removeCustomer() {
        System.out.println("Enter customer's ID");
        long customerId = scanner.nextLong();
        Optional<Customer> customer = customerRepository.findById(customerId);
        customer.ifPresentOrElse(customerRepository::delete, () -> System.out.println("There are no customers with the indicated ID"));
    }

    public void showAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        if(customers!=null){
            customers.forEach(System.out::println);
        }else System.out.println("The list is empty.");
    }
}

