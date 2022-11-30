package PersonService.dto;


import PersonService.enums.MessagesPermission;
import PersonService.enums.RoleType;
import PersonService.enums.StatusCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    private Long id;

    @Email
    @NotNull
    @JsonProperty(required = true)
    private String email;

    private String phone;

    private String photo;

    private String about;

    private StatusCode statusCode;

    @NotNull
    @JsonProperty(required = true)
    private String firstName;

    @NotNull
    @JsonProperty(required = true)
    private String lastName;

    private LocalDate birthDay;

    private MessagesPermission messagesPermission;

    private LocalDateTime lastOnlineTime;

    @Builder.Default
    private Boolean isOnline = true;

    @Builder.Default
    @JsonProperty(required = true)
    private Boolean isBlocked = false;

    @Builder.Default
    private Boolean isDeleted = false;

    @NotNull
    @JsonProperty(required = true)
    private Set<RoleType> roles;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

    private AddressDTO address;

    private String password;
}
