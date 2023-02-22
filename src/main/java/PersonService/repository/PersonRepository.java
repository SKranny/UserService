package PersonService.repository;

import PersonService.repository.specifications.PersonSpecification;
import PersonService.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor {

    List<Person> findAllByIdIn(Collection<Long> friendsId);

    List<Person> findAllByIsDeletedIsFalse();
    Optional<Person> findPersonByEmail(String email);

    Optional<Person> findPersonById(Long id);

    default Page<Person> findAllBySearchFilter(String address, String firstName, String lastName, Integer fromAge, Integer toAge,
                                               Pageable pageable) {

        Specification<Person> specification = Specification.where(PersonSpecification.checkFirstName(firstName))
                .and(PersonSpecification.checkLastName(lastName))
                .and(PersonSpecification.checkAddress(address))
                .and(PersonSpecification.ageBetween(fromAge, toAge));
        return this.findAll(specification, pageable);
    }

    default List<Person> findAllBySearchInNames(String userName) {
        Specification<Person> specification = Specification.where(PersonSpecification.checkFirstName(userName))
                .or(PersonSpecification.checkLastName(userName));
        return this.findAll(specification);
    }
}
