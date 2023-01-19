package PersonService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePersonRequest {

    String firstName;

    String lastName;

    String birthDate;

    String phone;

    String about;

    String country;

    String city;
}
