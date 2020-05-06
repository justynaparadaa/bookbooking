package pl.jparada.bookbooking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jparada.bookbooking.repository.model.BookingDetails;

@Repository
public interface BookingDetailsRepository extends CrudRepository<BookingDetails, Long> {

    BookingDetails getByBookedBook_Id(Long id);
}
