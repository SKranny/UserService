package PersonService;


import PersonService.mappers.PersonMapper;
import PersonService.mappers.RoleSetMapper;
import PersonService.model.Person;
import PersonService.model.Role;
import constants.MessagesPermission;
import constants.RoleType;
import constants.StatusCode;
import dto.userDto.PersonDTO;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@SpringBootTest
public class PersonMapperTest {


    private PersonMapper personMapper = Mappers.getMapper(PersonMapper.class);

    @Autowired
    private RoleSetMapper roleSetMapper;
    Person person = Person.builder()
            .id(1L)
            .email("some@mail.com")
            .phone("+79050534533")
            .photo("someurl.com")
            .about("I like to drink coffee")
            .statusCode(StatusCode.NONE)
            .roles(roleSetMapper.toRoleSet(Collections.singleton(RoleType.ROLE_USER)))
            .firstName("Evgeny")
            .lastName("Smirnov")
            .birthDay(LocalDate.of(1995, 12, 19))
            .messagesPermission(MessagesPermission.ALL)
            .lastOnlineTime(LocalDateTime.of(2022, 12, 05, 22, 35))
            .build();


    PersonDTO personDTO = PersonDTO.builder()
            .id(1L)
            .email("some@mail.com")
            .phone("+79050534533")
            .photo("someurl.com")
            .about("I like to drink coffee")
            .statusCode(StatusCode.NONE)
            .firstName("Evgeny")
            .lastName("Smirnov")
            .birthDay(LocalDate.of(1995, 12, 19))
            .messagesPermission(MessagesPermission.ALL)
            .lastOnlineTime(LocalDateTime.of(2022, 12, 05, 22, 35))
            .build();


    @Test
    public void mapPersonToDtoTest() {



        PersonDTO personDTO = personMapper.toPersonDTO(person);

        Assertions.assertNotNull(personDTO);
        Assertions.assertEquals(person.getId(), personDTO.getId());
        Assertions.assertEquals(person.getEmail(), personDTO.getEmail());
        Assertions.assertEquals(person.getPhone(), personDTO.getPhone());
        Assertions.assertEquals(person.getAbout(), personDTO.getAbout());
        Assertions.assertEquals(person.getStatusCode().toString(), personDTO.getStatusCode().toString());
        Assertions.assertEquals(person.getFirstName(), personDTO.getFirstName());
        Assertions.assertEquals(person.getLastName(), personDTO.getLastName());
        Assertions.assertEquals(person.getBirthDay(), personDTO.getBirthDay());
        Assertions.assertEquals(person.getMessagesPermission(), personDTO.getMessagesPermission());
        Assertions.assertEquals(person.getLastOnlineTime(), personDTO.getLastOnlineTime());

    }


    @Test
    public void mapDtoToPersonTest() {

        Person person = personMapper.toPerson(personDTO);

        Assertions.assertNotNull(personDTO);
        Assertions.assertEquals(personDTO.getId(), person.getId());
        Assertions.assertEquals(personDTO.getEmail(), person.getEmail());
        Assertions.assertEquals(personDTO.getPhone(), person.getPhone());
        Assertions.assertEquals(personDTO.getAbout(), person.getAbout());
        Assertions.assertEquals(personDTO.getStatusCode().toString(), person.getStatusCode().toString());
        Assertions.assertEquals(personDTO.getFirstName(), person.getFirstName());
        Assertions.assertEquals(personDTO.getLastName(), person.getLastName());
        Assertions.assertEquals(personDTO.getBirthDay(), person.getBirthDay());
        Assertions.assertEquals(personDTO.getMessagesPermission(), person.getMessagesPermission());
        Assertions.assertEquals(personDTO.getLastOnlineTime(), person.getLastOnlineTime());

    }
}
