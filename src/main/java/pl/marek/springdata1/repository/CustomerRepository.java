package pl.marek.springdata1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.marek.springdata1.model.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByPesel(String pesel);
}
