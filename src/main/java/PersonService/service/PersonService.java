package PersonService.service;

import PersonService.dto.LoginRequest;
import dto.userDto.PersonDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface PersonService {

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

    PersonDTO editMyAccount();

    PersonDTO deleteMyAccount();

    PersonDTO searchByFilter();

    PersonDTO search();

    List<Long> getAllIds();

}
