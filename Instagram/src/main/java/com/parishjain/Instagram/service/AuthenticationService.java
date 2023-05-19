package com.parishjain.Instagram.service;

import com.parishjain.Instagram.models.AuthenticationToken;
import com.parishjain.Instagram.models.InstaUser;
import com.parishjain.Instagram.repository.IAuthenticationRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    IAuthenticationRepo authenticationRepo;

    public void save(AuthenticationToken authenticationToken) {
        authenticationRepo.save(authenticationToken);
    }


    @Transactional
    public ResponseEntity<String> signout(InstaUser instaUser) {
        try{
            authenticationRepo.deleteByAuthInstaUser(instaUser);
            return ResponseEntity.ok().body("User Sign out Successfully");
        }
        catch (Exception ex){
            return ResponseEntity.badRequest().body("Exception : " + ex);
        }
    }

    public boolean authenticate(String email, String token) {

        // CHECK IF EMAIL OR TOKEN IS NULL OR NOT
        if(email == null || token == null){
            return false;
        }

        AuthenticationToken authToken = authenticationRepo.findFirstByAuthToken(token);

        if(authToken == null) return false;

        String authEmail = authToken.getAuthInstaUser().getUserEmail();

        return authEmail.equals(email);
    }

    public InstaUser findUserByToken(String token) {

        return authenticationRepo.findFirstByAuthToken(token).getAuthInstaUser();
    }
}
