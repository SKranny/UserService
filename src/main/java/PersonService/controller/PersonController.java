package PersonService.controller;

import PersonService.dto.LoginRequest;
import PersonService.dto.UpdatePersonRequest;
import PersonService.service.PersonService;
import dto.userDto.PersonDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
@Tag(name="Person Service", description="Работа с аккаунтом")
public class PersonController {
    private final PersonService personService;

    @GetMapping("/{id}")
    private PersonDTO getPersonById(@PathVariable Long id) {
        return personService.getPersonById(id);
    }

    @Operation(summary = "Создание пользователя")
    @PostMapping
    public PersonDTO createPerson(@Valid @RequestBody PersonDTO personDTO) {
         return personService.createPerson(personDTO);
    }

    @Operation(summary = "Получение пользователя по email")
    @GetMapping("/{email}")
    public PersonDTO getPersonDTOByEmail(@PathVariable(name = "email") String email) {
       return personService.findByEmail(email);
    }

    @Operation(summary = "Получить всех пользователей")
    @GetMapping
    public List<PersonDTO> getAllPersonsDTO() {
        return personService.findAllAccounts();
    }

    @Operation(summary = "Восстановить пользователя")
    @PutMapping("/recovery")
    public PersonDTO recoveryPerson(@RequestBody LoginRequest loginRequest) {
        return personService.recoveryPerson(loginRequest);
    }

    @Operation(summary = "Заблокировать пользователя по ID")
    @PutMapping("/block/{id}")
    public PersonDTO blockAccountById(@PathVariable Long id) {
        return personService.blockById(id);
    }

    @Operation(summary = "Разблокировать пользователя по ID")
    @DeleteMapping("/block/{id}")
    public PersonDTO unblockAccountById(@PathVariable Long id) {
        return personService.unblockById(id);
    }

    @Operation(summary = "Получение аккаунта")
    @GetMapping("/me")
    public PersonDTO getMyAccount(Principal principal) {
        return personService.getMyAccount(principal.getName());
    }

    @Operation(summary = "Получение аккаунта")
    @PutMapping("/me")
    public PersonDTO editMyAccount(@RequestBody UpdatePersonRequest updatePersonRequest, Principal principal) {
        return personService.editMyAccount(principal.getName(), updatePersonRequest);
    }

    @Operation(summary = "Удаление аккаунта")
    @DeleteMapping("/me")
    public PersonDTO deleteMyAccount(Principal principal) {
        return personService.deleteMyAccount(principal.getName());
    }

    @Operation(summary = "Поиск аккаунта по фильтру")
    @PostMapping("/searchByFilter")
    public PersonDTO SearchByFilter() {
        return personService.searchByFilter();
    }

    @Operation(summary = "Поиск аккаунта")
    @GetMapping("/search")
    public PersonDTO searchAccount() {
        return personService.search();
    }

    @Operation(summary = "Получение всех ID аккаунтов")
    @GetMapping("/ids")
    public List<Long> getAllIds() {
        return personService.getAllIds();
    }

    @Operation(summary = "Получение аккаунтов по их ID")
    @GetMapping("/accountIds")
    public String getAccountByIds() {
        return "search all accounts emulation";
    }


    @Operation(summary = "Обновить данные пользователя")
    @PutMapping
    public void updateCustomer(@RequestBody PersonDTO personDTO) {
        personService.updateCustomer(personDTO);
    }

    @Operation(summary = "Загрузить фото")
    @PostMapping("/upload")
    public String uploadPhoto(@RequestBody MultipartFile file, Long id) {
        return personService.uploadPhoto(file, id);
    }
}
