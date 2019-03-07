package pl.marekhacieja.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import pl.marekhacieja.model.Client;
import pl.marekhacieja.model.Order;

@Repository
@Transactional
public class ClientDao extends GenericDao<Client, Long> {
	
	public void removeAllOrders(Client client) {
		Client managedClient = get(client.getId());
		for (Order order : managedClient.getOrders())
			order.setClient(null);
		managedClient.getOrders().clear();
	}
}
