package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate birthDay;
    private String photo;
    private String about;
    private String countryAndCity;
    private LocalDate lastOnlineTime;

}
