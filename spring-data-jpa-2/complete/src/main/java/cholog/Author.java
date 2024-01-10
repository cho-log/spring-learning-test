package cholog;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Person person;

    @OneToMany(mappedBy = "author")
    private Set<BookAuthor> books = new HashSet<>();

    public Author(Person person) {
        this.person = person;
    }

    public Author() {

    }

    public Long getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public Set<BookAuthor> getBooks() {
        return books;
    }
}
