package pl.jparada.bookbooking.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jparada.bookbooking.repository.model.Book;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    @Query("SELECT b FROM Book b left join BookingDetails o on b.id = o.bookedBook where o.bookedBook is NULL")
    List<Book> getAvailableBooks();
}
