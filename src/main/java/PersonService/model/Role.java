package PersonService.model;

import PersonService.enums.RoleType;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_id_gen")
    @SequenceGenerator(name = "person_id_gen", sequenceName = "person_id_seq", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleType role;

}
