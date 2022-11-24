package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;


public interface PersonService {

    public void createPerson(Person person);


    public Person findByEmail(String email);
}
