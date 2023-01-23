package PersonService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdatePersonRequest {
    @NotBlank
    String firstName;

    @NotBlank
    String lastName;

    @NotBlank
    String birthDate;

    @NotBlank
    String phone;

    @NotBlank
    String about;

    @NotBlank
    String country;

    @NotBlank
    String city;
}
