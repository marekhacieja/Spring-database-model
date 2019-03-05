package pl.marekhacieja.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import pl.marekhacieja.model.Client;

@Repository
@Transactional
public class ClientDao extends GenericDao<Client, Long> {
}
