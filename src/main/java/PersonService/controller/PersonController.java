package PersonService.controller;

import PersonService.service.PersonService;
import dto.userDto.PersonDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class PersonController {
    private final PersonService personService;

    @PostMapping
    public PersonDTO createPerson(@Valid @RequestBody PersonDTO personDTO) {
         return personService.createPerson(personDTO);
    }

    @GetMapping("/{email}")
    public PersonDTO getPersonDTOByEmail(@PathVariable(name = "email") String email) {
       return personService.findByEmail(email);
    }

    @PutMapping
    public void updateCustomer(@RequestBody PersonDTO personDTO) {
        personService.updateCustomer(personDTO);
    }

    @PostMapping("/upload")
    public PersonDTO uploadPhoto(@RequestBody MultipartFile file, Integer id) throws Exception {
        return personService.uploadPhoto(file,id);
    }
}
