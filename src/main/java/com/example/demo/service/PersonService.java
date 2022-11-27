package com.example.demo.service;

import com.example.demo.dto.PersonDTO;
import com.example.demo.model.Person;


public interface PersonService {

    public void createPerson(Person person);


    public Person findByEmail(String email);
}
