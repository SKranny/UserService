package PersonService.service;

import PersonService.dto.LoginRequest;
import PersonService.dto.UpdatePersonRequest;
import PersonService.exception.PersonException;
import PersonService.mappers.PersonMapper;
import PersonService.model.Address;
import PersonService.model.Person;
import PersonService.repository.AddressRepository;
import PersonService.repository.PersonRepository;
import aws.AwsClient;
import dto.userDto.PersonDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    private final AddressRepository addressRepository;

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
        return personRepository.findPersonByEmail(email).map(personMapper::toPersonDTOWithoutAddress)
                .orElseThrow(() -> new PersonException("Error! Unknown person", HttpStatus.UNAUTHORIZED));
    }

    @Override
    public PersonDTO editMyAccount(String email, UpdatePersonRequest updatePersonRequest) {
        Optional<Person> person = personRepository.findPersonByEmail(email);
        personRepository.save(personInfoUpdate(person.get(), updatePersonRequest));
        return person.map(personMapper::toPersonDTO).get();
    }

    private Person personInfoUpdate(Person person, UpdatePersonRequest updatePersonRequest){
        person.setFirstName(updatePersonRequest.getFirstName());
        person.setLastName(updatePersonRequest.getLastName());
        person.setBirthDay(LocalDate.parse(updatePersonRequest.getBirthDate()));
        person.setPhone(updatePersonRequest.getPhone());
        person.setAbout(updatePersonRequest.getAbout());
        addressUpdate(person,updatePersonRequest);
        return person;
    }

    private void addressUpdate(Person person, UpdatePersonRequest updatePersonRequest){
        if (!checkPersonAddress(person,updatePersonRequest)){
            person.setAddress(findAddress(updatePersonRequest));
        }
    }

    private Address findAddress(UpdatePersonRequest updatePersonRequest){
        Address address = addressRepository.findAddressByCountry(updatePersonRequest.getCountry())
                .orElseThrow(() -> new PersonException("Error! Address not found!"));
        if (address.getCity().equals(updatePersonRequest.getCity())){
            return address;
        }else {
            Address newAddress = new Address();
            newAddress.setCountry(updatePersonRequest.getCountry());
            newAddress.setCity(updatePersonRequest.getCity());
            return newAddress;
        }
    }

    private boolean checkPersonAddress(Person person, UpdatePersonRequest updatePersonRequest){
        return person.getAddress().getCountry().equals(updatePersonRequest.getCountry()) &&
                person.getAddress().getCity().equals(updatePersonRequest.getCity());
    }

    @Override
    public PersonDTO deleteMyAccount(String email) {
        Optional<Person> tempPerson = Optional.empty();
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
    public PersonDTO search(){
        Optional<Person> tempPerson = Optional.empty();
        return tempPerson.map(personMapper::toPersonDTOWithoutAddress).orElseThrow(() ->
                new PersonException("Warning! Установлена заглушка на search!", HttpStatus.BAD_REQUEST));
    }

}
