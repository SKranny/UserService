package PersonService.mappers;

import PersonService.model.Role;
import constants.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoleSetMapper {
    private final RoleMapper roleMapper;

    public Set<Role> toRoleSet(Set<RoleType> roleTypes) {
        return roleTypes.stream()
                .map(roleMapper::toRole)
                .collect(Collectors.toSet());
    }

    public Set<RoleType> toRoleTypeSet(Set<Role> roles) {
        //Здесь проверить, если персона пуста, то создать новый персона узера

        return roles.stream()
                .map(roleMapper::toRoleType)
                .collect(Collectors.toSet());
    }
}
