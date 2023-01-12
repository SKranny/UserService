package PersonService.service;

import PersonService.aws.AwsClient;
import PersonService.exception.PersonException;
import PersonService.mappers.PersonMapper;
import PersonService.model.Person;
import PersonService.repository.PersonRepository;
import dto.userDto.PersonDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


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
    public PersonDTO uploadPhoto(MultipartFile file,Integer id) throws IOException {
        Person person = personRepository.findById(id).get();
        person.setPhoto(awsClient.uploadImage(file));
        return personMapper.toPersonDTO(personRepository.save(person));
    }
}
