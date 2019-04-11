package pl.marek.springdata1.app;

import org.springframework.stereotype.Controller;
import pl.marek.springdata1.services.AuthorService;
import pl.marek.springdata1.services.BookService;
import pl.marek.springdata1.services.CustomerService;
import pl.marek.springdata1.services.RentService;

import java.util.Arrays;
import java.util.Scanner;

@Controller
public class AppController {

    private Scanner scanner;
    private BookService bookService;
    private AuthorService authorService;
    private CustomerService customerService;
    private RentService rentService;

    public AppController(Scanner scanner, BookService bookService, AuthorService authorService, CustomerService customerService, RentService rentService) {
        this.scanner = scanner;
        this.bookService = bookService;
        this.authorService = authorService;
        this.customerService = customerService;
        this.rentService = rentService;
    }

    public void mainLoop() {
        Options option;
        do {
            printOptions();
            option = readOption();
            executeOption(option);
        } while(option != Options.EXIT);
    }

    private void printOptions() {
        System.out.println("Menu:");
        Arrays.stream(Options.values()).forEach(System.out::println);
    }

    private Options readOption() {
        boolean correctOptionSelected = false;
        Options option = null;
        while(!correctOptionSelected) {
            System.out.println("Choose an option:");
            int optionId = scanner.nextInt();
            scanner.nextLine();
            try {
                option = Options.chooseOption(optionId);
                correctOptionSelected = true;
            } catch(InvalidOptionException e) {
                System.err.println(e.getMessage());
            }
        }
        return option;
    }

    private void executeOption(Options option) {
        switch (option) {
            case ADD_BOOK:
                bookService.createBook();
                break;
            case ADD_AUTHOR:
                authorService.createAuthor();
                break;
            case ADD_CUSTOMER:
                customerService.createCustomer();
                break;
            case SHOW_BOOKS:
                bookService.showAllBooks();
                break;
            case SHOW_CUSTOMERS:
                customerService.showAllCustomers();
                break;
            case SHOW_AUTHORS:
                authorService.showAllAuthors();
                break;
            case RENT:
                rentService.rentBookToCustomer();
                break;
            case REMOVE_DEVICE:
                bookService.removeBook();
                break;
            case REMOVE_CATEGORY:
                authorService.removeAuthor();
                break;
            case REMOVE_CUSTOMER:
                customerService.removeCustomer();
                break;
            case EXIT:
                closeApp();
        }
    }

    private void closeApp() {
        scanner.close();
    }
}