package rz.demo.boot.data.envers.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

/**
 * @author Rashidi Zin
 */
public interface BookRepository extends JpaRepository<Book, Long> {

    Stream<Book> findAllByAuthor(String author);

}
