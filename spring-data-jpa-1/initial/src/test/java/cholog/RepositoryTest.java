package cholog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void findAll() {
        customerRepository.save(new Customer("Jack", "Bauer"));
        customerRepository.save(new Customer("Chloe", "O'Brian"));

        Iterable<Customer> customers = customerRepository.findAll();
        assertThat(customers).extracting(Customer::getFirstName).containsOnly("Jack", "Chloe");
    }

    @Test
    void findById() {
        customerRepository.save(new Customer("Jack", "Bauer"));
        customerRepository.save(new Customer("Chloe", "O'Brian"));

        Customer customer = customerRepository.findById(1L).orElseThrow(IllegalArgumentException::new);
        assertThat(customer.getFirstName()).isEqualTo("Jack");
    }

    @Test
    void count() {
        customerRepository.save(new Customer("Jack", "Bauer"));
        customerRepository.save(new Customer("Chloe", "O'Brian"));

        long count = customerRepository.count();
        assertThat(count).isEqualTo(2);
    }

    @Test
    void delete() {
        customerRepository.save(new Customer("Jack", "Bauer"));
        customerRepository.save(new Customer("Chloe", "O'Brian"));

        customerRepository.deleteById(1L);
        assertThat(customerRepository.count()).isEqualTo(1);
    }
}
