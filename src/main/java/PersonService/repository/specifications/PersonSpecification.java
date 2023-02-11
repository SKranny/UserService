package PersonService.repository.specifications;

import PersonService.model.Person;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.Locale;

public class PersonSpecification {

    public static Specification<Person> checkFirstName(String name) {
        if (name == null) {
            return null;
        }
        String regExp = "%" + name.toLowerCase(Locale.ROOT) + "%";
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.lower(root.get("firstName")), regExp);
    }

    public static Specification<Person> checkLastName(String name) {
        if (name == null) {
            return null;
        }
        String regExp = "%" + name.toLowerCase(Locale.ROOT) + "%";
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.lower(root.get("lastName")), regExp);
    }

    public static Specification<Person> checkAddress(String address) {
        if (address == null) {
            return null;
        }
        String regExp = "%" + address.toLowerCase(Locale.ROOT) + "%";
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(
                criteriaBuilder.lower(root.get("address")), regExp);
    }

    public static Specification<Person> ageBetween(Integer minAge, Integer maxAge) {
        if (minAge == null && maxAge == null) {
            return null;
        }
        LocalDate DateFrom = (maxAge == null) ? LocalDate.of(1, 1, 1) : LocalDate.now().minusYears(maxAge);
        LocalDate DateTo = (minAge == null) ? LocalDate.now() : LocalDate.now().minusYears(minAge);
        return ((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.between(root.get("birthDay"), DateFrom, DateTo));
    }
}
