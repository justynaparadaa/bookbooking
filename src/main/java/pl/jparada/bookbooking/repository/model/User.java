package pl.jparada.bookbooking.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickName;
    private String password;

    @OneToMany
    private List<BookingDetails> bookingDetails;

    public User(String nickName, String password) {
        this.nickName = nickName;
        this.password = password;
    }


}
