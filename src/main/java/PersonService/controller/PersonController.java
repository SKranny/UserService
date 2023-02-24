package PersonService.controller;

import PersonService.dto.LoginRequest;
import PersonService.dto.UpdatePersonRequest;
import PersonService.service.PersonService;
import dto.userDto.PersonDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import security.TokenAuthentication;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
@Tag(name="Person Service", description="Работа с аккаунтом")
public class PersonController {
    final long MAX_AGE = 200;
    private final PersonService personService;

    @GetMapping("/info/{id}")
    private PersonDTO getPersonById(@PathVariable(name = "id") Long id) {
        return personService.getPersonById(id);
    }

    @Operation(summary = "Получить всех пользователей")
    @GetMapping("find")
    public List<PersonDTO> findPersonsByFriend() {
        return personService.findPersonsByFriend();
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
    public PersonDTO getMyAccount(TokenAuthentication authentication) {
        return personService.getMyAccount(authentication.getTokenData().getEmail());
    }

    @Operation(summary = "Корректировка аккаунта")
    @PutMapping("/me")
    public PersonDTO editMyAccount(@Valid @RequestBody UpdatePersonRequest updatePersonRequest, TokenAuthentication authentication) {
        return personService.editMyAccount(authentication.getTokenData().getEmail(), updatePersonRequest);
    }

    @Operation(summary = "Удаление аккаунта")
    @DeleteMapping("/me")
    public PersonDTO deleteMyAccount(TokenAuthentication authentication) {
        return personService.deleteMyAccount(authentication.getTokenData().getEmail());
    }

    @Operation(summary = "Поиск аккаунта по фильтру")
    @PostMapping("/searchByFilter")
    public PersonDTO SearchByFilter() {
        return personService.searchByFilter();
    }

    @Operation(summary = "Поиск по адресу, имени, диапазону возрастов ")
    @GetMapping("/search")
    public Page<PersonDTO> searchAccounts(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "address", required = false) String address,
            @Valid @Min(0) @Max(MAX_AGE) @RequestParam(value = "ageFrom", required = false) Integer ageMin,
            @Valid @Min(0) @Max(MAX_AGE) @RequestParam(value = "ageTo", required = false) Integer ageMax,
            @Valid @Min(0) @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "offset", defaultValue = "20", required = false) Integer limit
    ) {
        return personService.search(address, firstName, lastName,  ageMin, ageMax, PageRequest.of(page, limit));

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

    @Operation(summary = "Удалить фото")
    @DeleteMapping("/delete")
    public String deletePhoto(Long id){
        return personService.deletePhoto(id);
    }

    @Operation(summary = "Дать пользвателю по ID админские права")
    @PutMapping("/admin/{id}")
    public PersonDTO addAdminAccountById(@PathVariable Long id) {
        return personService.addAdminRoleById(id);
    }

    @Operation(summary = "отобрать у пользователя по ID админские права")
    @DeleteMapping("/admin/{id}")
    public PersonDTO addAdminRolebyId(@PathVariable Long id) {
        return personService.delAdminRoleById(id);
    }
}
