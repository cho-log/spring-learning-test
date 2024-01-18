package cholog;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class OneToOneTest {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private PersonRepository personRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void uniDirection() {
        Person person = new Person("사람");
        entityManager.persist(person);

        Author author = new Author(person);
        entityManager.persist(author);

        entityManager.flush();
        entityManager.clear();

        Author persistAuthor = entityManager.find(Author.class, author.getId());
        assertThat(persistAuthor).isNotNull();
        assertThat(persistAuthor.getPerson()).isNotNull();
    }

    @Test
    void biDirection() {
        Person person = new Person("사람");
        entityManager.persist(person);

        Author author = new Author(person);
        entityManager.persist(author);

        entityManager.flush();
        entityManager.clear();

        Person persistPerson = entityManager.find(Person.class, person.getId());
        assertThat(persistPerson).isNotNull();
        assertThat(persistPerson.getAuthor()).isNotNull();
    }

    @Test
    void findByIdForAuthor() {
        Person person = new Person("사람");
        entityManager.persist(person);

        Author author = new Author(person);
        entityManager.persist(author);

        entityManager.flush();
        entityManager.clear();

        Optional<Author> persistAuthor = authorRepository.findById(author.getId());
        assertThat(persistAuthor).isPresent();
        assertThat(persistAuthor.get().getPerson()).isNotNull();
    }

    @Test
    void findByIdForPerson() {
        Person person = new Person("사람");
        entityManager.persist(person);

        Author author = new Author(person);
        entityManager.persist(author);

        entityManager.flush();
        entityManager.clear();

        Optional<Person> persistPerson = personRepository.findById(person.getId());
        assertThat(persistPerson).isPresent();
        assertThat(persistPerson.get().getAuthor()).isNotNull();
    }
}
