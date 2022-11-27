package com.example.demo.mappers;

import com.example.demo.dto.PersonDTO;
import com.example.demo.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
public interface PersonMapper {


    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);
    PersonDTO personToPersonDTO(Person entity);


    Person personDTOtoPerson(PersonDTO dto);
}
