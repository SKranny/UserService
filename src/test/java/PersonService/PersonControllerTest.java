package PersonService;

import PersonService.controller.PersonController;
import PersonService.service.PersonService;

import com.fasterxml.jackson.databind.ObjectMapper;
import constants.MessagesPermission;
import constants.RoleType;
import constants.StatusCode;
import dto.userDto.PersonDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {


    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    private PersonService personService;
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
    public void createPersonTest() throws Exception {


        Mockito.when(personService.createPerson(personDTO)).thenReturn(personDTO);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/v1/account")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(personDTO));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.email", Matchers.is("some@mail.com")))
                .andExpect(jsonPath("$.phone", Matchers.is("+79050534533")))
                .andExpect(jsonPath("$.about", Matchers.is("I like to drink coffee")))
                .andExpect(jsonPath("$.phone", Matchers.is("+79050534533")))
                .andExpect(jsonPath("$.statusCode", Matchers.is(StatusCode.NONE.toString())))
                .andExpect(jsonPath("$.firstName", Matchers.is("Evgeny")))
                .andExpect(jsonPath("$.lastName", Matchers.is("Smirnov")));
               // .andExpect(jsonPath("$.roles", Matchers.is(RoleType.ROLE_USER)));


    }


    @Test
    public void getPersonByEmailTest() throws Exception {
        Mockito.when(personService.findByEmail("some@mail.com")).thenReturn(personDTO);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/account/some@mail.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.email", Matchers.is("some@mail.com")))
                .andExpect(jsonPath("$.phone", Matchers.is("+79050534533")))
                .andExpect(jsonPath("$.about", Matchers.is("I like to drink coffee")))
                .andExpect(jsonPath("$.phone", Matchers.is("+79050534533")))
                .andExpect(jsonPath("$.statusCode", Matchers.is(StatusCode.NONE.toString())))
                .andExpect(jsonPath("$.firstName", Matchers.is("Evgeny")))
                .andExpect(jsonPath("$.lastName", Matchers.is("Smirnov")));

    }

}