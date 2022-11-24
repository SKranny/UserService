package com.example.demo.model;

import com.example.demo.enums.MessagesPermission;
import com.example.demo.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table (name = "person")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column (name = "first_name")
    private String firstName;
    @Column (name = "last_name")
    private String lastName;
    @Column (name = "reg_date")
    private LocalDate regDate;
    @Column (name = "birth_day")
    private LocalDate birthDay;
    @Column (name = "e_mail")
    private String email;
    @Column (name = "password")
    private String password;
    @Column(name = "phone")
    private String phone;
    @Column (name = "photo")
    private String photo;
    @Column (name = "about")
    private String about;
    @Column (name = "town")
    private String countryAndCity;
    @Column (name = "confirmation_code")
    private Integer confirmationCode;
    @Column (name = "is_approved")
    private Boolean isApproved;
    @Enumerated(EnumType.ORDINAL)
    @Column (name = "messages_permission")
    private MessagesPermission messagesPermission;
    @Column (name = "last_online_time")
    private LocalDate lastOnlineTime;
    @Column (name = "is_blocked")
    private Boolean isBlocked;
    @Enumerated(EnumType.ORDINAL)
    @Column (name = "role")
    private Role role;

}
