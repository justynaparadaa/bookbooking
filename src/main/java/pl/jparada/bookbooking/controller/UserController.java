package pl.jparada.bookbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.jparada.bookbooking.exception.BookBorrowedException;
import pl.jparada.bookbooking.repository.model.BookingDetails;
import pl.jparada.bookbooking.repository.model.User;
import pl.jparada.bookbooking.service.BookService;
import pl.jparada.bookbooking.service.BookingDetailsService;
import pl.jparada.bookbooking.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookingDetailsService bookingDetailsService;

    @Autowired
    private BookService bookService;

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity bookBookByUser(@PathVariable(value = "id") Long userId, @RequestParam Long bookId) {
        try {
            bookingDetailsService.bookBook(bookId);
            BookingDetails bookingDetailsId = bookingDetailsService.getBookedBookByBookId(bookId);
            userService.addBookingDetailsToUser(userId, bookingDetailsId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("UDALO SIE WYPOZYCZYC!");
        } catch (BookBorrowedException ex) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("SORRY, WYPOZYCZONA!");
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserDetails(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @GetMapping("/users/{id}/bookingdetails")
    public ResponseEntity<List<BookingDetails>> getUserBookingDetails (@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getUserBookingDetails(id));
    }

    @DeleteMapping("/users/{userId}/bookingdetails/{bookingId}")
    public void returnBookByUser(@PathVariable(value = "userId") Long userId, @PathVariable(value = "bookingId") Long bookingId){
        BookingDetails bookingDetailsByBookingId = bookingDetailsService.getBookedBookByBookingId(bookingId);
        userService.getUserById(userId).getBookingDetails().remove(bookingDetailsByBookingId);
        bookingDetailsService.removeBookedBook(bookingDetailsByBookingId);
    }




}
