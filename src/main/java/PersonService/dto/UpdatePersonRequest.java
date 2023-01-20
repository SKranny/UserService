package PersonService.dto;

import lombok.Data;

@Data
public class UpdatePersonRequest {

    String firstName;

    String lastName;

    String birthDate;

    String phone;

    String about;

    String country;

    String city;
}
