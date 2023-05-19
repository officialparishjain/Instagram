package com.parishjain.Instagram.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class InstaUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull
    @Column(nullable = false)
    private String userFirstName;

    @Column(nullable = false)
    @NotNull
    private String userLastName;

    @NotNull
    private LocalDate userDob;

    @Email
    @NotNull
    @Column(nullable = false,unique = true)
    private String userEmail;

    @Pattern(regexp = "^\\+\\d{2}-\\d{10}$",message = "Phone number should be in format +XX-XXXXXXXXXX")
    private String userPhoneNumber;

    @NotNull
    @Column(nullable = false)
    private String userPassword;

    @Column(unique = true)
    @NotNull(message = "Username cannot be null")
//    @Max(value = 20,message = "Maximum Length of username should be 20")
//    @Min(value = 5,message = "Minimum length of username should be 5..")
    private String username;

    private String userBio;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean isBlueTicked;

    public InstaUser(String userFirstName, String userLastName, LocalDate userDob, String userEmail, String userPhoneNumber, String userPassword, String username, String userBio) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userDob = userDob;
        this.userEmail = userEmail;
        this.userPhoneNumber = userPhoneNumber;
        this.userPassword = userPassword;
        this.username = username;
        this.userBio = userBio;
    }
}
