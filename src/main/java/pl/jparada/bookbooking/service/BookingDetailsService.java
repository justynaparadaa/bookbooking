package pl.jparada.bookbooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pl.jparada.bookbooking.exception.BookBorrowedException;
import pl.jparada.bookbooking.repository.model.Book;
import pl.jparada.bookbooking.repository.model.BookingDetails;
import pl.jparada.bookbooking.repository.BookRepository;
import pl.jparada.bookbooking.repository.BookingDetailsRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BookingDetailsService {

    @Autowired
    private BookingDetailsRepository bookingDetailsRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<BookingDetails> getAllBookedBooks() {
        return (List<BookingDetails>) bookingDetailsRepository.findAll();
    }

    public List<Book> getAllAvailableBooks() {
        return bookRepository.getAvailableBooks();
    }

    public void bookBook(Long id) {
        boolean result = getAllAvailableBooks()
                .stream()
                .anyMatch(book -> book.getId().equals(id));
        if (!result) {
            throw new BookBorrowedException(HttpStatus.INTERNAL_SERVER_ERROR, "Książka jest wypożyczona!");
        } else {
            Optional<Book> book = bookRepository.findById(id);
            BookingDetails bookingDetails = new BookingDetails();
            bookingDetails.setBookedBook(book.get());
            bookingDetails.setDateStart(LocalDate.now());
            bookingDetails.setDateEnd(LocalDate.now().plusDays(30));
            bookingDetailsRepository.save(bookingDetails);
        }
    }

    public BookingDetails getBookedBookByBookingId(Long bookingId) {
        return bookingDetailsRepository.findById(bookingId).get();
    }

    public void removeBookedBook(BookingDetails bookingDetailsByBookingId) {
        bookingDetailsRepository.delete(bookingDetailsByBookingId);
    }

    public List<Book> getAvailableBooksByTitle(String title) {
        return getAllAvailableBooks()
                .stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> getAvailableBooksByAuthor(String author) {
        return getAllAvailableBooks()
                .stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList());
    }

    public BookingDetails getBookedBookByBookId(Long id) {
        return bookingDetailsRepository.getByBookedBook_Id(id);
    }

}
