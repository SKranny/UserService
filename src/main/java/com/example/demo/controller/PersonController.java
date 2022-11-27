package com.example.demo.controller;

import com.example.demo.dto.PersonDTO;
import com.example.demo.mappers.PersonMapper;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {
    private final PersonService personService;
    private final PersonMapper personMapper;
    public PersonController(PersonService personService, PersonMapper personMapper) {
        this.personService = personService;
        this.personMapper = personMapper;
    }

    @PostMapping("/person")
    public void  add(@RequestBody PersonDTO personDTO) {
         personService.createPerson(personMapper.personDTOtoPerson(personDTO));


    }

    @GetMapping("/person/{email}")
    public Person getByEmail(@PathVariable(name = "email") String email) {
       return personService.findByEmail(email);
    }

}
