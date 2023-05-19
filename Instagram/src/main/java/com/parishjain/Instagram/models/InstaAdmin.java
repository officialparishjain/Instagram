package com.parishjain.Instagram.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class InstaAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    @NotNull
    @Column(nullable = false)
    private String adminFirstName;

    @NotNull
    @Column(nullable = false)
    private String adminLastName;

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@admin\\.insta\\.com$",message = "Admin should be [user@admin.insta.com]")
    @Column(unique = true)
    private String adminEmail;
}
