package pl.marekhacieja;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import pl.marekhacieja.dao.*;
import pl.marekhacieja.model.*;

@SpringBootApplication
public class SpringJpaBookstoreApplication {

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext ctx = SpringApplication.run(SpringJpaBookstoreApplication.class, args);
		// Create clients
		Client client = new Client("Jan", "Nowak", "Katowicka 23, Mysłowice");

		// ### Create orders ###
		Order order = new Order("05/03/2019 r.");

		// ### Create books ###	
		List<Book> books = new ArrayList<>();
		books.add(new Book("Silmarillion", "J.R.R. Tolkien", "fantasy", "Nowa"));
		books.add(new Book("Harry Potter i Kamień Filozoficzny", "J.K. Rowling", "fantasy", "Bloomsbury"));
		books.add(new Book("Harry Potter i Komnata Tajemnic", "J.K. Rowling", "fantasy", "Bloomsbury"));
		books.add(new Book("Jesienne werble", "Diana Gabaldon", "horror", "Świat Książki"));
		
		// ### Add books ###
		BookDao bookDao = ctx.getBean(BookDao.class);
		books.forEach(bookDao::save);

		// ### Books functions ###
		System.out.println("All Books:");					
	    bookDao.getAll().forEach(System.out::println);
		
	    System.out.println("books by: J.K. Rowling ");
	    bookDao.getByAuthor("J.K. Rowling").forEach(System.out::println);
	    
		bookDao.deleteByTitle("Harry Potter i Kamień Filozoficzny");
	    System.out.println("Books without Harry Potter i Kamień Filozoficzny:");
	    bookDao.getAll().forEach(System.out::println);
	    
	    System.out.println("Delete all");
	    bookDao.deleteAll();
	    bookDao.getAll().forEach(System.out::println);

		// ### Add orders ###
		client.addOrder(order);

		// ### Add clients ###
		ClientDao clientDao = ctx.getBean(ClientDao.class);
		clientDao.save(client);

		// ### Remove orders ###
		clientDao.removeAllOrders(client);

		ctx.close();
	}
}
