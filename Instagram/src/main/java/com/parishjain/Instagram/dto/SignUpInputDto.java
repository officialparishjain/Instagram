package com.parishjain.Instagram.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpInputDto {

    @NotNull
    private String userFirstName;

    @NotNull
    private String userLastName;

    @NotNull
    private LocalDate userDob;

    @Email
    @NotNull
    private String userEmail;

    @Pattern(regexp = "^\\+\\d{2}-\\d{10}$")
    private String userPhoneNumber;

    @NotNull
    private String username;

    private String userBio;

    @NotNull
    private String userPassword;

}
