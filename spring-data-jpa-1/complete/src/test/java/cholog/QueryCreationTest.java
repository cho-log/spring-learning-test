package cholog;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class QueryCreationTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void findByLastName() {
        entityManager.persist(new Customer("Jack", "Bauer"));
        entityManager.persist(new Customer("Chloe", "O'Brian"));

        List<Customer> customers = customerRepository.findByLastName("O'Brian");
        assertThat(customers).extracting(Customer::getFirstName).containsOnly("Chloe");
    }

    @Test
    void findByLastNameIgnoreCase() {
        entityManager.persist(new Customer("Jack", "Bauer"));
        entityManager.persist(new Customer("Chloe", "O'Brian"));
        entityManager.persist(new Customer("Kim", "Bauer"));
        entityManager.persist(new Customer("David", "Palmer"));
        entityManager.persist(new Customer("Michelle", "Dessler"));

        List<Customer> customers = customerRepository.findByLastNameIgnoreCase("Bauer");
        assertThat(customers).extracting(Customer::getFirstName).containsOnly("Jack", "Kim");
    }

    @Test
    void findByLastNameOrderByFirstNameDesc() {
        entityManager.persist(new Customer("Jack", "Bauer"));
        entityManager.persist(new Customer("Chloe", "O'Brian"));
        entityManager.persist(new Customer("Kim", "Bauer"));
        entityManager.persist(new Customer("David", "Palmer"));
        entityManager.persist(new Customer("Michelle", "Dessler"));

        List<Customer> customers = customerRepository.findByLastNameOrderByFirstNameDesc("Bauer");
        assertThat(customers).extracting(Customer::getFirstName).containsExactly("Kim", "Jack");

    }
}
