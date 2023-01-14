package PersonService.service;

import PersonService.dto.LoginRequest;
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

import java.util.List;
import java.util.Optional;
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
    @Override
    public List<PersonDTO> findAllAccounts() {
        return personRepository.findAll()
                .stream()
                .map(personMapper::toPersonDTOWithoutAddress)
                .collect(Collectors.toList());
    }

    @Override
    public PersonDTO recoveryPerson(LoginRequest loginRequest) {
        Optional<Person> person = personRepository.findPersonByEmail(loginRequest.getEmail());
        if (person.isEmpty() || !person.get().getPassword().equals(loginRequest.getPassword())) {
            Optional<Person> tempPerson = Optional.empty();
            return tempPerson.map(personMapper::toPersonDTOWithoutAddress).orElseThrow(() ->
                    new PersonException("Error! User or email is incorrect!", HttpStatus.BAD_REQUEST));
        }
        person.get().setIsDeleted(false);
        return personMapper.toPersonDTOWithoutAddress(personRepository.save(person.get()));
    }

    private PersonDTO changeBlockState(Long id, boolean blockState) {
        Optional <Person> person = personRepository.findPersonById(id);
        if (person.isEmpty()){
            Optional<Person> tempPerson = Optional.empty();
            return tempPerson.map(personMapper::toPersonDTOWithoutAddress).orElseThrow(() ->
                    new PersonException("Error! Id is incorrect!", HttpStatus.BAD_REQUEST));
        }
        person.get().setIsBlocked(blockState);
        return personMapper.toPersonDTOWithoutAddress(personRepository.save(person.get()));
    }
    @Override
    public PersonDTO blockById(Long id) {
        return  changeBlockState(id, true);
    }

    @Override
    public PersonDTO unblockById(Long id) {
        return  changeBlockState(id, false);
    }

    @Override
    public List <Long> getAllIds(){
        return personRepository.findAll().stream().
                map(Person::getId).sorted().collect(Collectors.toList());
    }

    @Override
    public PersonDTO getMyAccount() {
        Optional<Person> tempPerson = Optional.empty();
        return tempPerson.map(personMapper::toPersonDTOWithoutAddress).orElseThrow(() ->
                new PersonException("Warning! Установлена заглушка на getMyAccount!", HttpStatus.BAD_REQUEST));
    }

    @Override
    public PersonDTO editMyAccount() {
        Optional<Person> tempPerson = Optional.empty();
        return tempPerson.map(personMapper::toPersonDTOWithoutAddress).orElseThrow(() ->
                new PersonException("Warning! Установлена заглушка на editMyAccount!", HttpStatus.BAD_REQUEST));
    }

    @Override
    public PersonDTO deleteMyAccount() {
        Optional<Person> tempPerson = Optional.empty();
        return tempPerson.map(personMapper::toPersonDTOWithoutAddress).orElseThrow(() ->
                new PersonException("Warning! Установлена заглушка на deleteMyAccount!", HttpStatus.BAD_REQUEST));
    }

    @Override
    public PersonDTO searchByFilter(){
        Optional<Person> tempPerson = Optional.empty();
        return tempPerson.map(personMapper::toPersonDTOWithoutAddress).orElseThrow(() ->
                new PersonException("Warning! Установлена заглушка на  searchByFilter!", HttpStatus.BAD_REQUEST));
    }

    @Override
    public PersonDTO search(){
        Optional<Person> tempPerson = Optional.empty();
        return tempPerson.map(personMapper::toPersonDTOWithoutAddress).orElseThrow(() ->
                new PersonException("Warning! Установлена заглушка на search!", HttpStatus.BAD_REQUEST));
    }

}
