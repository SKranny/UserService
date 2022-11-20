package com.example.demo.service;

import com.example.demo.enums.MessagesPermission;
import com.example.demo.enums.Role;
import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class PersonServiceTest{

    private PersonRepository personRepository;

    public PersonServiceTest(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void add(Person person){

        person.setFirstName("Витя");
        person.setLastName("Попов");
        person.setEmail("test2@gmail.com");
        person.setBirthDay(LocalDate.of(1995,11,11));
        person.setRegDate(LocalDate.of(2022,11,9));
        person.setAbout("I don`t know what to say about myself");
        person.setCountryAndCity("Russia, Yakutsk");
        person.setConfirmationCode(12345);
        person.setMessagesPermission(MessagesPermission.ALL);
        person.setLastOnlineTime(LocalDate.of(2022,11,9));

        person.setIsBlocked(false);
        person.setIsApproved(true);

        person.setRole(Role.ROLE_USER);
        person.setPassword("password123");

        personRepository.save(person);





    }
}
