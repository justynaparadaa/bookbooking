package pl.jparada.bookbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jparada.bookbooking.exception.BookBorrowedException;
import pl.jparada.bookbooking.repository.model.Book;
import pl.jparada.bookbooking.repository.model.BookingDetails;
import pl.jparada.bookbooking.service.BookService;
import pl.jparada.bookbooking.service.BookingDetailsService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BookingDetailsController {

    @Autowired
    private BookingDetailsService bookingDetailsService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookingDetailsController BookBorrowedException;

    @GetMapping("/bookingdetails")
    public ResponseEntity<List<BookingDetails>> getAllBookedBooks(){
       return ResponseEntity.ok().body(bookingDetailsService.getAllBookedBooks());
    }

    @GetMapping("/availablebooks")
    public ResponseEntity<List<Book>> getAllAvailableBooks(){
        return ResponseEntity.ok().body(bookingDetailsService.getAllAvailableBooks());
    }

    @PostMapping("/availablebooks/{id}")
    public ResponseEntity bookBook(@PathVariable(value = "id") Long id){
        try {
            bookingDetailsService.bookBook(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("UDALO SIE WYPOZYCZYC!");
        } catch (BookBorrowedException ex) {
           return ResponseEntity
                   .status(HttpStatus.OK)
                   .body("SORRY, WYPOZYCZONA!");
        }
    }

    @DeleteMapping("/bookingdetails/{id}")
    public void returnBook(@PathVariable(value = "id") Long bookingId){
        BookingDetails bookingDetailsByBookingId = bookingDetailsService.getBookedBookByBookingId(bookingId);
        bookingDetailsService.removeBookedBook(bookingDetailsByBookingId);
    }

    @GetMapping("/availablebooks/title/{title}")
    public ResponseEntity<List<Book>> getAvailableBookByTitle(@PathVariable(value = "title") String title) {
       return ResponseEntity.ok().body(bookingDetailsService.getAvailableBooksByTitle(title));
    }

    @GetMapping("/availablebooks/author")
    public ResponseEntity<List<Book>> getAvailableBookByAuthor(@RequestParam(value = "name") String author) {
        return ResponseEntity.ok().body(bookingDetailsService.getAvailableBooksByAuthor(author));
    }

}
