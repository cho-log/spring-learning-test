package cholog;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ManyToManyTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void intermediateEntity() {
        Person person = new Person("사람");
        entityManager.persist(person);

        Author author = new Author(person);
        entityManager.persist(author);

        Publisher publisher = new Publisher("출판사");
        entityManager.persist(publisher);

        Book book = new Book("책", publisher);
        entityManager.persist(book);

        BookAuthor bookAuthor = new BookAuthor(book, author);
        entityManager.persist(bookAuthor);

        entityManager.flush();
        entityManager.clear();

        Optional<Book> persistBook = bookRepository.findById(book.getId());
        assertThat(persistBook).isNotEmpty();
        assertThat(persistBook.get().getAuthors()).isNotEmpty();
    }
}
