package com.parishjain.Instagram.controller;

import com.parishjain.Instagram.dto.SignInInputDto;
import com.parishjain.Instagram.dto.SignInOutputDto;
import com.parishjain.Instagram.dto.SignUpInputDto;
import com.parishjain.Instagram.dto.SignUpOutputDto;
import com.parishjain.Instagram.models.InstaPostLike;
import com.parishjain.Instagram.service.AuthenticationService;
import com.parishjain.Instagram.service.FollowerService;
import com.parishjain.Instagram.service.InstaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/insta")
public class InstaUserController {

    @Autowired
    InstaUserService instaUserService;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    FollowerService followerService;

    // SIGN UP USER TO INSTAGRAM

    @PostMapping(value = "/signup")
    private ResponseEntity<SignUpOutputDto> signup(@RequestBody SignUpInputDto signUpInputDto){
        return instaUserService.signup(signUpInputDto);
    }


    // SIGN IN USER TO INSTAGRAM

    @GetMapping("/signin")
    private ResponseEntity<SignInOutputDto> signIn(@RequestBody SignInInputDto signInInputDto){
        return instaUserService.signIn(signInInputDto);
    }

    // SIGN OUT

    @DeleteMapping("/signout/{email}")
    private ResponseEntity<String> signout(@PathVariable String email){
        return instaUserService.signout(email);
    }


    // FOLLOW USER

    @PostMapping(value = "/follow/{email}/{token}/{otherId}")
    private ResponseEntity<String> followUser(@PathVariable String email,
                                              @PathVariable String token,
                                              @PathVariable Long otherId){

        // FIRST WE WILL DO AUTHENTICATION THAT USER IS VALID OR NOT

        String response;
        HttpStatus status;
        if(authenticationService.authenticate(email,token)){
            try{
                response = instaUserService.follow(email,otherId);
                status = HttpStatus.OK;
            }
            catch (Exception ex){
                response = "Exception occurred : " + ex;
                status = HttpStatus.BAD_REQUEST;
            }
        }
        else {
            response = "Invalid User";
            status = HttpStatus.FORBIDDEN;
        }
        return ResponseEntity.status(status).body(response);
    }


    // Post Like

    @PostMapping(value = "/like/{email}/{token}")
    ResponseEntity<String> likePost(@PathVariable String email,
                                    @PathVariable String token,
                                    @RequestBody InstaPostLike postLike){

        HttpStatus status;
        String response = null;
        try{
            authenticationService.authenticate(email,token);
            instaUserService.likePost(postLike);
            status = HttpStatus.OK;
        }
        catch (Exception ex){
            response = "Failure.. Exception Occurred " + ex;
            status = HttpStatus.FORBIDDEN;
        }
        return ResponseEntity.status(status).body(response);
    }

}
