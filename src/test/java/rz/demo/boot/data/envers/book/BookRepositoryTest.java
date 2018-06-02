package rz.demo.boot.data.envers.book;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

/**
 * @author Rashidi Zin
 */
@DataJpaTest
@RunWith(SpringRunner.class)
public class BookRepositoryTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private BookRepository repository;

    @Before
    public void save() {
        em.persistAndFlush(
                Book.builder().author("Rudyard Kipling").title("Jungle Book").build()
        );
    }

    @Test
    public void findAllByAuthor() {
        Stream<Book> booksByAuthor = repository.findAllByAuthor("Rudyard Kipling");

        assertThat(booksByAuthor)
                .isNotEmpty()
                .extracting(Book::getAuthor, Book::getTitle)
                .containsExactly(tuple("Rudyard Kipling", "Jungle Book"));
    }
}