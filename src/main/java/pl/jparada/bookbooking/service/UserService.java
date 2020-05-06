package pl.jparada.bookbooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jparada.bookbooking.repository.model.BookingDetails;
import pl.jparada.bookbooking.repository.model.User;
import pl.jparada.bookbooking.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public void addBookingDetailsToUser(Long id, BookingDetails bookingDetailsId) {
        User user = userRepository.findById(id).get();
        List<BookingDetails> bookingDetails = user.getBookingDetails();
        bookingDetails.add(bookingDetailsId);
//        user.setBookingDetails(bookingDetails);
        userRepository.save(user);
    }

    public List<BookingDetails> getUserBookingDetails(Long id) {
        return userRepository.findById(id).get().getBookingDetails();
    }


}
