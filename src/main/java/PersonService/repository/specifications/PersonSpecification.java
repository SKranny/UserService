package PersonService.repository.specifications;

import PersonService.model.Person;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.Locale;

public class PersonSpecification {

    public static Specification<Person> checkFirstName(String name) {
        String regExp = "%" + name.toLowerCase(Locale.ROOT) + "%";
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.lower(root.get("firstName")), regExp);
    }

    public static Specification<Person> checkLastName(String name) {
        String regExp = "%" + name + "%";
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.lower(root.get("lastName")), regExp);
    }

    public static Specification<Person> checkAddress(String address) {
        String regExp = "%" + address + "%";
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.lower(root.get("address")), regExp);
    }

    public static Specification<Person> ageBetween(Integer minAge, Integer maxAge) {
        LocalDate DateFrom = LocalDate.now().minusYears(minAge);
        LocalDate DateTo = LocalDate.now().minusYears(maxAge);
        return ((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.between(root.get("birthDay"), DateTo, DateFrom));
    }
}
