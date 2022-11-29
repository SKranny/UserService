package PersonService.repository;

import PersonService.enums.RoleType;
import PersonService.model.Role;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Cacheable(value = "role", key = "#roleType")
    Optional<Role> findByRole(RoleType roleType);
}
