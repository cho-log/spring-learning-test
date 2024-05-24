package cholog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class UpdatingDaoTest {
    private UpdatingDao updatingDao;
    private QueryingDao queryingDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        queryingDao = new QueryingDao(jdbcTemplate);
        updatingDao = new UpdatingDao(jdbcTemplate);

        jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE customers(" +
                "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

        List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);
    }

    @Test
    void insert() {
        Customer customer = new Customer("Leonor", "Watling");
        updatingDao.insert(customer);

        List<Customer> customers = queryingDao.findCustomerByFirstName("Leonor");

        assertThat(customers).hasSize(1);
    }

    @Test
    void delete() {
        int rowNum = updatingDao.delete(1L);

        assertThat(rowNum).isEqualTo(1);
    }

    @Test
    void keyHolder() {
        Customer customer = new Customer("Leonor", "Watling");
        Long id = updatingDao.insertWithKeyHolder(customer);

        assertThat(id).isNotNull();
    }
}
