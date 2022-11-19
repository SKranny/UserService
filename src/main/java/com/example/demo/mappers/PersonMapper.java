package com.example.demo.mappers;

import com.example.demo.dto.PersonDTO;
import com.example.demo.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PersonMapper {
    @Mapping(target="id", source="entity.id")
    @Mapping(target="firstName", source="entity.firstName")
    @Mapping(target="lastName", source="entity.lastName")
    @Mapping(target="birthDay", source="entity.birthDay")
    @Mapping(target="photo", source="entity.photo")
    @Mapping(target="about", source="entity.about")
    @Mapping(target="countryAndCity", source="entity.countryAndCity")
    @Mapping(target="lastOnlineTime", source="entity.lastOnlineTime")
    PersonDTO personToPersonDTO(Person entity);

    @Mapping(target="id", source="dto.id")
    @Mapping(target="firstName", source="dto.firstName")
    @Mapping(target="lastName", source="dto.lastName")
    @Mapping(target="birthDay", source="dto.birthDay")
    @Mapping(target="photo", source="dto.photo")
    @Mapping(target="about", source="dto.about")
    @Mapping(target="countryAndCity", source="dto.countryAndCity")
    @Mapping(target="lastOnlineTime", source="dto.lastOnlineTime")
    Person personDTOtoPerson(PersonDTO dto);
}
