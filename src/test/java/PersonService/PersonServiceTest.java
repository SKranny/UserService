package PersonService;

import PersonService.mappers.PersonMapper;
import PersonService.model.Person;
import PersonService.repository.PersonRepository;
import constants.MessagesPermission;
import dto.userDto.PersonDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.ProviderException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PersonServiceTest {


    @Mock
    private PersonRepository personRepository;

    private PersonMapper personMapper;
    private Person person;

    @Autowired
    public void setPersonMapper(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    @BeforeEach
    public void init() {
        person = Person.builder()
                .id(1L)
                .email("some@mail.com")
                .phone("+79050534533")
                .photo("someurl.com")
                .about("I like to drink coffee")
                .firstName("Evgeny")
                .lastName("Smirnov")
                .birthDay(LocalDate.of(1995, 12, 19))
                .messagesPermission(MessagesPermission.ALL)
                .lastOnlineTime(LocalDateTime.of(2022, 12, 05, 22, 35))
                .build();
    }

    @Test
    public void findByEmailTest() {
        when(personRepository.findPersonByEmail(person.getEmail())).thenReturn(Optional.of(person));
        person = personRepository.findPersonByEmail("some@mail.com").orElseThrow(
                () -> new ProviderException("Error! User with this email not found!"));
        PersonDTO personDTO = personMapper.toPersonDTO(person);

        assertNotNull(personDTO);
        assertEquals(1L, personDTO.getId());
        assertEquals("some@mail.com", personDTO.getEmail());
        assertEquals("someurl.com", personDTO.getPhoto());
        assertEquals("I like to drink coffee", personDTO.getAbout());
        assertEquals("Evgeny", personDTO.getFirstName());
        assertEquals("Smirnov", personDTO.getLastName());
        assertEquals(LocalDate.of(1995, 12, 19), personDTO.getBirthDay());
        assertEquals(MessagesPermission.ALL.toString(), personDTO.getMessagesPermission().toString());
        assertEquals(LocalDateTime.of(2022, 12, 05, 22, 35), personDTO.getLastOnlineTime());

    }


}
