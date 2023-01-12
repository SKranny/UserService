package PersonService.service;

import dto.userDto.PersonDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface PersonService {

    PersonDTO createPerson(PersonDTO personDTO);

    PersonDTO uploadPhoto(MultipartFile file, Integer id) throws Exception;

    PersonDTO findByEmail(String email);

    void updateCustomer(PersonDTO personDTO);
}
