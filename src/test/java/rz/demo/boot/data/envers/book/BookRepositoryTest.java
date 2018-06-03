package rz.demo.boot.data.envers.book;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.test.context.junit4.SpringRunner;
import rz.demo.boot.data.envers.RepositoryConfiguration;
import rz.demo.boot.data.envers.audit.AuditConfiguration;
import rz.demo.boot.data.envers.audit.AuditorAwareImpl;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE;

/**
 * @author Rashidi Zin
 */
@DataJpaTest(includeFilters = @Filter(
        type = ASSIGNABLE_TYPE,
        classes = {AuditorAwareImpl.class, AuditConfiguration.class, RepositoryConfiguration.class}
))
@RunWith(SpringRunner.class)
public class BookRepositoryTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private BookRepository repository;

    private Book book;

    @Before
    public void save() {
        book = em.persistAndFlush(
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

    @Test
    public void hasAuditInformation() {
        assertThat(book)
                .extracting(Book::getCreatedBy, Book::getCreatedDate, Book::getLastModifiedBy, Book::getLastModifiedDate, Book::getVersion)
                .isNotNull();
    }
}