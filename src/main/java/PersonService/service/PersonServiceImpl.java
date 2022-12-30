package PersonService.service;

import constants.RoleType;
import PersonService.exception.PersonException;
import PersonService.mappers.PersonMapper;
import PersonService.repository.PersonRepository;
import dto.userDto.PersonDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;


@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    @Override
    public PersonDTO createPerson(PersonDTO personDTO) {
        if(CollectionUtils.isEmpty(personDTO.getRoles())){
            personDTO.setRoles(Collections.singleton(RoleType.ROLE_USER));
        }
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
