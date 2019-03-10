package pl.marekhacieja.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import pl.marekhacieja.model.Order;
import pl.marekhacieja.model.Book;

@Repository
@Transactional
public class OrderDao extends GenericDao<Order, Long> {
}