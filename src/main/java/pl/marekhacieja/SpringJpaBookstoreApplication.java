package pl.marekhacieja;

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
		Book book1 = new Book("Silmarillion", "J.R.R. Tolkien", "fantasy", "Nowa");
		Book book2 = new Book("Harry Potter i Kamień Filozoficzny", "J.K. Rowling", "fantasy", "Bloomsbury");
		Book book3 = new Book("Jesienne werble", "Diana Gabaldon", "horror", "Świat Książki");

		// ### Add books ###
		order.getBooks().add(book1);
		order.getBooks().add(book2);
		order.getBooks().add(book3);

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
