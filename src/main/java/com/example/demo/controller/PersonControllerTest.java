package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.service.PersonServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonControllerTest {
    @Autowired
    private PersonServiceTest personServiceTest;

    public PersonControllerTest(PersonServiceTest personServiceTest) {
        this.personServiceTest = personServiceTest;
    }

    @PostMapping ("/ptest")
    public void add(@RequestBody Person person){
        personServiceTest.add(person);
    }
}
