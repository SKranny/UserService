package PersonService.repository;

import PersonService.repository.specifications.PersonSpecification;
import PersonService.model.Person;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor {

    List<Person> findAllByIdIn(Collection<Long> friendsId);
    List<Person> findAllByIsBlockedIsFalse();

    List<Person> findAllByIsBlockedIsTrue();


    List<Person> findAllByIsDeletedIsFalse();
    Optional<Person> findPersonByEmail(String email);

    Optional<Person> findPersonById(Long id);

    List<Person> findAllPersonsByCreatedOnBetween(LocalDate date1, LocalDate date2);

    default List<Person> findAllBySearchFilter(String address, Integer fromAge, Integer toAge) {
        Specification<Person> specification = Specification.where(PersonSpecification.checkAddress(address))
                .and(PersonSpecification.ageBetween(fromAge, toAge));
        return this.findAll(specification);
    }

    default List<Person> findAllBySearchSubStringInNames(String userName) {
        Specification<Person> specification = Specification.where(PersonSpecification.checkFirstName(userName))
                .or(PersonSpecification.checkLastName(userName));
        return this.findAll(specification);
    }
}
