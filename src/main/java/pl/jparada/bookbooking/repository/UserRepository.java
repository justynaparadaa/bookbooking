package pl.jparada.bookbooking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jparada.bookbooking.repository.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
