package PersonService.service;

import dto.userDto.PersonDTO;
import org.springframework.stereotype.Service;


public interface PersonService {

    PersonDTO createPerson(PersonDTO personDTO);

    PersonDTO findByEmail(String email);

    void updateCustomer(PersonDTO personDTO);
}
