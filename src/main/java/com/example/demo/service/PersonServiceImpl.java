package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.stereotype.Service;


@Service
public class PersonServiceImpl implements PersonService{

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void createPerson(Person person) {

        personRepository.save(person);

    }

    @Override
    public Person findByEmail(String email) {
       Person person = personRepository.findPersonByEmail(email);
       System.out.println(person);
       return person;

    }
}
