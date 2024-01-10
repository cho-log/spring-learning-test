package cholog;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Author(Person person) {
    }

    public Author() {
    }

    public Long getId() {
        return id;
    }

    public Person getPerson() {
        return null;
    }
}
