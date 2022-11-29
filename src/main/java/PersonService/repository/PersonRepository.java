package PersonService.repository;

import PersonService.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository <Person, Integer> {
    Optional<Person> findPersonByEmail(String email);
}
