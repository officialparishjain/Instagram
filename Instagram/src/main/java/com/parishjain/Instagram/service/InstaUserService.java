package com.parishjain.Instagram.service;

import com.parishjain.Instagram.dto.SignInInputDto;
import com.parishjain.Instagram.dto.SignInOutputDto;
import com.parishjain.Instagram.dto.SignUpInputDto;
import com.parishjain.Instagram.dto.SignUpOutputDto;
import com.parishjain.Instagram.models.AuthenticationToken;
import com.parishjain.Instagram.models.InstaPostLike;
import com.parishjain.Instagram.models.InstaUser;
import com.parishjain.Instagram.repository.InstaUserRepo;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class InstaUserService {

    @Autowired
    InstaUserRepo instaUserRepo;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    FollowerService followerService;

    @Autowired
    FollowingService followingService;

    @Autowired
    InstaPostLikeService instaPostLikeService;

    public ResponseEntity<SignUpOutputDto> signup(SignUpInputDto signUpInputDto) {

        // FIRST WE WILL CHECK USER IS ALREADY PRESENT IN DATABASE OR NOT
        String email = signUpInputDto.getUserEmail();
        Boolean isPresent = instaUserRepo.existsByUserEmail(email);
        if(isPresent){
            // USER IS ALREADY EXIST
            return ResponseEntity.badRequest().body(
                    new SignUpOutputDto("Failure",
                            "User with this email is already exist..")
            );
        }
        else{
            // NOW THIS MEANS THAT USER IS NOT PRESENT SO WE HAVE TO REGISTER THE USER

            // FIRST WE WILL ENCRYPT THE MESSAGE

            String encryptPassword = null;
            try{
                 encryptPassword =  generateEncryptPassword(signUpInputDto.getUserPassword());
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            // NOW CONVERT IT TO USER

            InstaUser instaUser = new InstaUser(
                    signUpInputDto.getUserFirstName(),
                    signUpInputDto.getUserLastName(),
                    signUpInputDto.getUserDob(),
                    signUpInputDto.getUserEmail(),
                    signUpInputDto.getUserPhoneNumber(),
                    encryptPassword,
                    signUpInputDto.getUsername(),
                    signUpInputDto.getUserBio()
            );

            // Now save
            instaUserRepo.save(instaUser);
            return ResponseEntity.ok().body(new SignUpOutputDto("Success","User Signup successfully.."));
        }
    }

    private String generateEncryptPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        // Get the user's password as a byte array and update the message digest with it
        messageDigest.update(password.getBytes());
        // Calculate the MD5 hash of the password
        byte[] digested = messageDigest.digest();
        // Concert Byte Array to hexadecimal String
        return DatatypeConverter.printHexBinary(digested);

    }

    public ResponseEntity<SignInOutputDto> signIn(SignInInputDto signInInputDto) {

        // CHECK USER IS PRESENT OR NOT
        String email = signInInputDto.getEmail();
        Boolean isPresent = instaUserRepo.existsByUserEmail(email);

        if(!isPresent){
            return ResponseEntity.badRequest().body(new SignInOutputDto("Failure","User not present.."));
        }
        else{

            String encryptPassword = null;
            try{
                encryptPassword =  generateEncryptPassword(signInInputDto.getPassword());
            }
            catch (Exception ex){
                return ResponseEntity.badRequest().body(
                        new SignInOutputDto("Failure",
                                "Exception occurred "+ ex ));
            }

            // FIRST GET USER FROM EMAIL
            InstaUser instaUser = instaUserRepo.findFirstByUserEmail(signInInputDto.getEmail());
            // CORRESPONDING PASSWORD
            String password = instaUser.getUserPassword();

            // Matching Password
            boolean isValidUser = password.equals(encryptPassword);

            if(isValidUser){

                //  USER  IS A VALID USER THEN WE WILL GENERATE A TOKEN A RETURN TO THE USER
                AuthenticationToken authenticationToken = new AuthenticationToken(instaUser);

                // NOW WE WILL SAVE THE AUTHENTICATION TOKEN
                authenticationService.save(authenticationToken);

                return ResponseEntity.ok().body(new SignInOutputDto(authenticationToken.getAuthToken(),"Success.."));
            }
            else{
                return ResponseEntity.badRequest().body(new SignInOutputDto("Failure","Password Mismatch..."));
            }
        }



    }

    public ResponseEntity<String> signout(String email) {
        InstaUser instaUser = instaUserRepo.findFirstByUserEmail(email);
        return authenticationService.signout(instaUser);
    }

    public ResponseEntity<String> toggleBlueTick(Long id, Boolean blueTick) {

        // FIRST GET THE USER BY ID
        InstaUser instaUser = instaUserRepo.findByUserId(id);
        HttpStatus status;
        String response;
        if(instaUser != null){
            instaUser.setBlueTicked(blueTick);
            instaUserRepo.save(instaUser);
            response = "Blue tick was set to : " + blueTick;
            status = HttpStatus.OK;
        }
        else{
            status = HttpStatus.FORBIDDEN;
            response = "User Doesn't exist..";
        }

        return ResponseEntity.status(status).body(response);
    }

    public String follow(String email, Long otherId) {

        InstaUser otherUser = instaUserRepo.findByUserId(otherId);
        InstaUser instaUser = instaUserRepo.findFirstByUserEmail(email);
        Long myId = instaUser.getUserId();
        if(myId == otherId){
            return "Can't follow yourself...";
        }

        if(instaUser != null && otherUser != null){

            // WE WILL NOW START THE PROCEDURE OF FOLLOW

            // FIRST --- FOLLOW FROM MY SIDE
            // I WILL FOLLOW MEANS FOLLOWING WILL INCREASE FORM MY SIDE
            followingService.saveFollowing(instaUser,otherUser);


            // SECOND --- Follower FROM OTHER SIDE
            followerService.saveFollower(otherUser,instaUser);

            return "Followed Successfully!!!!!";
        }
        else
        {
            return "Users are invalid!!!";
        }

    }

    public void likePost(InstaPostLike postLike) {
        instaPostLikeService.like(postLike);
    }
}
