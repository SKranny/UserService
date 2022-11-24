package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/person")
    public void add(@RequestBody Person person) {
        personService.createPerson(person);
    }

    @GetMapping("/person/{email}")
    public Person getByEmail(@PathVariable(name = "email") String email) {
       return personService.findByEmail(email);
    }

}
