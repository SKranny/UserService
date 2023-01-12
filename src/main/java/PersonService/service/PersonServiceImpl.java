package PersonService.service;

import PersonService.exception.PersonException;
import PersonService.mappers.PersonMapper;
import PersonService.model.Person;
import PersonService.repository.PersonRepository;
import aws.AwsClient;
import dto.userDto.PersonDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    private final AwsClient awsClient;

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

    @Override
    public PersonDTO uploadPhoto(MultipartFile file,Integer id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonException("Error! Person not found!"));
        setPersonPhoto(person, file);

        return personMapper.toPersonDTO(personRepository.save(person));
    }

    private void setPersonPhoto(Person person, MultipartFile file) {
        try {
            person.setPhoto(awsClient.uploadImage(file));
        } catch (Exception ex) {
            throw new PersonException(ex, HttpStatus.BAD_REQUEST);
        }
    }
}
