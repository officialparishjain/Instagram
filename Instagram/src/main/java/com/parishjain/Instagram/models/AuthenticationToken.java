package com.parishjain.Instagram.models;

/*
    This class is Responsible for creating the authentication token.
*/

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class AuthenticationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authTokenId;
    private String authToken;
    private LocalDate authTokenDate;
    @OneToOne
    @JoinColumn(nullable = false,name = "fk_user_id")
    private InstaUser authInstaUser;


    public AuthenticationToken( InstaUser authInstaUser) {
        this.authToken = UUID.randomUUID().toString();
        this.authTokenDate = LocalDate.now();
        this.authInstaUser = authInstaUser;
    }
}
