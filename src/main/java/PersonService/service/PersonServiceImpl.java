package PersonService.service;

import PersonService.dto.PersonDTO;
import PersonService.exception.PersonException;
import PersonService.mappers.PersonMapper;
import PersonService.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    @Override
    public PersonDTO createPerson(PersonDTO personDTO) {
        return personMapper.toPersonDTO(personRepository.save(personMapper.toPerson(personDTO)));
    }

    @Override
    public PersonDTO findByEmail(String email) {
        return personRepository.findPersonByEmail(email)
                .map(personMapper::toPersonDTOWithoutAddress)
                .orElseThrow(() -> new PersonException("Error! User with this email not found!"));
    }

    @Override
    public void updateCustomer(PersonDTO personDTO) {
        personRepository.save(personMapper.toPerson(personDTO));
    }
}
