package cholog;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Set;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Book() {

    }

    public Book(String name, Publisher publisher) {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Publisher getPublisher() {
        return null;
    }

    public Set<Author> getAuthors() {
        return null;
    }
}
