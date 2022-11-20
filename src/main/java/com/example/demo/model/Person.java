package com.example.demo.model;

import com.example.demo.enums.MessagesPermission;
import com.example.demo.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table (name = "person")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @Column (name = "messages_permission")
    private MessagesPermission messagesPermission;
    @Column (name = "last_online_time")
    private LocalDate lastOnlineTime;
    @Column (name = "is_blocked")
    private Boolean isBlocked;
    @Column (name = "role")
    private Role role;

}
