package PersonService.mappers;

import PersonService.model.Person;
import PersonService.dto.PersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring",
        uses = {RoleSetMapper.class, AddressMapper.class},
        imports = LocalDateTime.class)
public interface PersonMapper {
    PersonDTO toPersonDTO(Person person);

    @Mapping(target = "address", ignore = true)
    PersonDTO toPersonDTOWithoutAddress(Person person);

    @Mapping(target = "statusCode", defaultValue = "NONE")
    @Mapping(target = "lastOnlineTime", defaultExpression = "java(LocalDateTime.now())")
    @Mapping(target = "isOnline", defaultValue = "true")
    @Mapping(target = "isBlocked", defaultValue = "false")
    @Mapping(target = "isDeleted", defaultValue = "false")
    @Mapping(target = "createdOn", defaultExpression = "java(LocalDateTime.now())")
    @Mapping(target = "updatedOn", defaultExpression = "java(LocalDateTime.now())")
    Person toPerson(PersonDTO dto);
}
