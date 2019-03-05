package pl.marekhacieja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import pl.marekhacieja.dao.*;
import pl.marekhacieja.model.*;

@SpringBootApplication
public class SpringJpaBookstoreApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(SpringJpaBookstoreApplication.class, args);

		Client client = new Client("Jan", "Nowak", "Katowicka 23, Mysłowice");
		ClientDao clientDao = ctx.getBean(ClientDao.class);
		clientDao.save(client);
		System.out.println(client);

		Order order = new Order("05/03/2019 r.");
		order.setClient(client);
		OrderDao orderDao = ctx.getBean(OrderDao.class);
		orderDao.save(order);

		Book book1 = new Book("Silmarillion", "J.R.R. Tolkien", "fantasy", "Nowa");
		Book book2 = new Book("Harry Potter i Kamień Filozoficzny", "J.K. Rowling", "fantasy", "Bloomsbury");
		Book book3 = new Book("Jesienne werble", "Diana Gabaldon", "horror", "Świat Książki");

		BookDao bookDao = ctx.getBean(BookDao.class);
		bookDao.save(book1);
		bookDao.save(book2);
		bookDao.save(book3);

		orderDao.addProductsToOrder(order.getId(), book1, book2, book3);
		ctx.close();
	}
}
