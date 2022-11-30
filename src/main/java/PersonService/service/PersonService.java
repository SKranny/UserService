package PersonService.service;

import PersonService.dto.PersonDTO;


public interface PersonService {

    PersonDTO createPerson(PersonDTO personDTO);

    PersonDTO findByEmail(String email);

    void updateCustomer(PersonDTO personDTO);
}
