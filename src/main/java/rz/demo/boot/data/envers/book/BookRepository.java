package rz.demo.boot.data.envers.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.stream.Stream;

/**
 * @author Rashidi Zin
 */
public interface BookRepository extends JpaRepository<Book, Long>, RevisionRepository<Book, Long, Integer> {

    Stream<Book> findAllByAuthor(String author);

}
