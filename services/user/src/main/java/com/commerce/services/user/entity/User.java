package com.commerce.services.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String userId;

    @Size(min = 3, max = 20)
    @NotBlank
    private String firstName;

    @Size(max = 20)
    private String middleName = "";

    @Size(min = 3, max = 20)
    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @Past
    @NotNull
    private LocalDate birthdate;

    @OneToOne
    private Address address;
}
