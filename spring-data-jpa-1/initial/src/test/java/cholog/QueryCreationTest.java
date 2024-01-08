package cholog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class QueryCreationTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void findByLastName() {
        customerRepository.save(new Customer("Jack", "Bauer"));
        customerRepository.save(new Customer("Chloe", "O'Brian"));

        List<Customer> customers = customerRepository.findByLastName("O'Brian");
        assertThat(customers).extracting(Customer::getFirstName).containsOnly("Chloe");
    }

    @Test
    void findByLastNameIgnoreCase() {
        customerRepository.save(new Customer("Jack", "Bauer"));
        customerRepository.save(new Customer("Chloe", "O'Brian"));
        customerRepository.save(new Customer("Kim", "Bauer"));
        customerRepository.save(new Customer("David", "Palmer"));
        customerRepository.save(new Customer("Michelle", "Dessler"));

        List<Customer> customers = customerRepository.findByLastNameIgnoreCase("Bauer");
        assertThat(customers).extracting(Customer::getFirstName).containsOnly("Jack", "Kim");
    }

    @Test
    void findByLastNameOrderByFirstNameDesc() {
        customerRepository.save(new Customer("Jack", "Bauer"));
        customerRepository.save(new Customer("Chloe", "O'Brian"));
        customerRepository.save(new Customer("Kim", "Bauer"));
        customerRepository.save(new Customer("David", "Palmer"));
        customerRepository.save(new Customer("Michelle", "Dessler"));

        List<Customer> customers = customerRepository.findByLastNameOrderByFirstNameDesc("Bauer");
        assertThat(customers).extracting(Customer::getFirstName).containsExactly("Kim", "Jack");

    }
}
