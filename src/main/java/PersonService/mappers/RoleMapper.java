package PersonService.mappers;

import PersonService.exception.PersonException;
import PersonService.model.Role;
import PersonService.repository.RoleRepository;
import constants.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleMapper {
    private final RoleRepository repository;

    public Role toRole(RoleType roleType) {
        return repository.findByRole(roleType)
                .orElseThrow(() -> new PersonException("Error! Invalid role!"));
    }

    public RoleType toRoleType(Role role) {
        return role.getRole();
    }
}
