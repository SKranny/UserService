package PersonService.service;

import PersonService.dto.LoginRequest;
import PersonService.dto.UpdatePersonRequest;
import PersonService.exception.PersonException;
import PersonService.mappers.PersonMapper;
import PersonService.model.Person;
import PersonService.repository.PersonRepository;
import aws.AwsClient;
import dto.userDto.PersonDTO;
import kafka.annotation.SubmitToKafka;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    private final AwsClient awsClient;

    @Override
    @SubmitToKafka(topic = "NewCustomer")
    public PersonDTO createPerson(PersonDTO personDTO) {
        Person person = personRepository.save(personMapper.toPerson(personDTO));
        return personMapper.toPersonDTO(person);
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
    public String uploadPhoto(MultipartFile file, Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonException("Error! Person not found!"));
        setPersonPhoto(person, file);
        return personRepository.save(person).getPhoto();
    }

    private void setPersonPhoto(Person person, MultipartFile file) {
        try {
            person.setPhoto(awsClient.uploadImage(file));
        } catch (Exception ex) {
            throw new PersonException(ex, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public String deletePhoto(Long id){
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonException("Error! Person not found!"));
        deletePersonPhoto(person);
        return personRepository.save(person).getPhoto();
    }

    private void deletePersonPhoto(Person person){
        String key = null;
        String regex = "/skillboxjava31/";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(person.getPhoto());
        while (matcher.find()){
            Integer start = matcher.end();
            key = person.getPhoto().substring(start);
        }
        person.setPhoto(null);
        awsClient.deleteImage(key);
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
    public PersonDTO getPersonById(Long id) {
        return personRepository.findPersonById(id)
                .map(personMapper::toPersonDTOWithoutAddress)
                .orElseThrow(() -> new PersonException("Error! User with this email not found!"));
    }

    @Override
    public PersonDTO getMyAccount(String email) {
        return personRepository.findPersonByEmail(email).map(personMapper::toPersonDTO)
                .orElseThrow(() -> new PersonException("Error! Unknown person", HttpStatus.UNAUTHORIZED));
    }

    @Override
    public PersonDTO editMyAccount(String email, UpdatePersonRequest updatePersonRequest) {
        return personRepository.findPersonByEmail(email)
                .map(person -> personInfoUpdate(person,updatePersonRequest))
                .map(personRepository::save)
                .map(personMapper::toPersonDTO)
                .orElseThrow(() -> new PersonException("Error! User not found", HttpStatus.BAD_REQUEST));
    }

    private Person personInfoUpdate(Person person, UpdatePersonRequest updatePersonRequest){
        person.setFirstName(updatePersonRequest.getFirstName());
        person.setLastName(updatePersonRequest.getLastName());
        person.setBirthDay(LocalDate.parse(updatePersonRequest.getBirthDate(), DateTimeFormatter.ISO_DATE_TIME));
        person.setPhone(updatePersonRequest.getPhone());
        person.setAbout(updatePersonRequest.getAbout());
        person.setAddress(updatePersonRequest.getAddress());
        return person;
    }

    @Override
    public PersonDTO deleteMyAccount(String email) {
        Optional<Person> tempPerson = personRepository.findPersonByEmail(email);
        tempPerson.get().setIsDeleted(true);
        personRepository.save(tempPerson.get());
        return tempPerson.map(personMapper::toPersonDTOWithoutAddress).orElseThrow(() ->
                new PersonException("Error! User not found", HttpStatus.BAD_REQUEST));
    }

    @Override
    public PersonDTO searchByFilter(){
        Optional<Person> tempPerson = Optional.empty();
        return tempPerson.map(personMapper::toPersonDTOWithoutAddress).orElseThrow(() ->
                new PersonException("Warning! Установлена заглушка на  searchByFilter!", HttpStatus.BAD_REQUEST));
    }

    @Override
    public Page<PersonDTO> search(String word, Pageable pageable) {
        Page<PersonDTO> persons =
                personRepository.findAllByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(word, word,  pageable)
                .map(personMapper::toPersonDTO);
        return persons;
    }

//    @Override
//    public <List> PersonDTO search(){
//        Optional<Person> tempPerson = Optional.empty();
////        return tempPerson.map(personMapper::toPersonDTOWithoutAddress).orElseThrow(() ->
////                new PersonException("Warning! Установлена заглушка на search!", HttpStatus.BAD_REQUEST));
//        return null;
//    }

}
