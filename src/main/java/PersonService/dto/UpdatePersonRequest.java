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
    @JsonProperty(required = true)
    String address;
}
