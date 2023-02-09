package PersonService.repository;

import PersonService.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository <Person, Long> {
    Optional<Person> findPersonByEmail(String email);
    Optional<Person> findPersonById(Long id);
//    Page<Person> findAllByFirstNameOrLastName(String firstName, String lastName, Pageable pageable);
    Page<Person> findAllByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName, Pageable pageable);
}
