package cholog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EntityMappingTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void mapEntity() {
        customerRepository.save(new Customer("Jack", "Bauer"));

        Iterable<Customer> customers = customerRepository.findAll();
        assertThat(customers).extracting(Customer::getFirstName).containsOnly("Jack");

        customerRepository.deleteAll();
        assertThat(customerRepository.findAll()).isEmpty();
    }
}
