package PersonService.service;

import PersonService.dto.LoginRequest;
import PersonService.dto.UpdatePersonRequest;
import dto.userDto.PersonDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface PersonService {

    List<PersonDTO> findPersonsByFriend();

    List<PersonDTO> findAllActivePersons();
    List<PersonDTO> findAllBlockedPersons();

    PersonDTO createPerson(PersonDTO personDTO);

    String uploadPhoto(MultipartFile file, Long id);

    String deletePhoto(Long id);

    PersonDTO findByEmail(String email);

    void updateCustomer(PersonDTO personDTO);

    List<PersonDTO> findAllAccounts();

    PersonDTO recoveryPerson(LoginRequest loginRequest);

    PersonDTO blockById(Long id);

    PersonDTO unblockById(Long id);

    PersonDTO getPersonById(Long id);

    PersonDTO getMyAccount(String email);

    PersonDTO editMyAccount(String email, UpdatePersonRequest updatePersonRequest);

    PersonDTO deleteMyAccount(String email);

    PersonDTO searchByFilter();

    Page<PersonDTO> search(String address, String firstName, String lastName, Integer ageFrom, Integer ageTo,
                           Pageable pageable);

    List<Long> getAllIds();

}
