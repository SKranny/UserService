package PersonService.model;

import PersonService.mappers.enums.StatusCode;
import constants.MessagesPermission;
import lombok.*;
import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;



@Entity
@Table(name = "person", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_id_gen")
    @SequenceGenerator(name = "person_id_gen", sequenceName = "person_id_seq", allocationSize = 1)
    private Long id;

    @NotNull
    private String email;

    private String phone;

    private String photo;

    private String about;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private StatusCode statusCode = StatusCode.NONE;

    private String firstName;

    private String lastName;

    private LocalDate birthDay;

    @Enumerated(EnumType.STRING)
    private MessagesPermission messagesPermission;

    @Builder.Default
    private LocalDateTime lastOnlineTime = LocalDateTime.now();

    @Builder.Default
    private Boolean isOnline = true;

    @Builder.Default
    private Boolean isBlocked = false;

    @Builder.Default
    private Boolean isDeleted = false;

    @NotNull
    @Column(nullable = false)
//   @ManyToMany(fetch = FetchType.EAGER)
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "person2role",
            joinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

    @Builder.Default
    private LocalDateTime createdOn = LocalDateTime.now();

    @Builder.Default
    private LocalDateTime updatedOn = LocalDateTime.now();

    private String address;

    private String password;
}
