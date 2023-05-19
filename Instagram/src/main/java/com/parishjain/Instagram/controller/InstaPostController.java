package com.parishjain.Instagram.controller;

import com.parishjain.Instagram.models.InstaPost;
import com.parishjain.Instagram.models.InstaUser;
import com.parishjain.Instagram.service.AuthenticationService;
import com.parishjain.Instagram.service.InstaPostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class InstaPostController {

    @Autowired
    AuthenticationService authService;

    @Autowired
    InstaPostService instaPostService;

    // ADD POST
    @PostMapping(value = "/add")
    public ResponseEntity<String> addPost(@Valid @RequestParam String email , @RequestParam String token , @RequestBody InstaPost post){
        HttpStatus status;
        String msg = "";
        if(authService.authenticate(email,token))
        {
          // MEAN USER IS AUTHENTICATED USER NOW HE/SHE CAN CREATE THE POST
          InstaUser instaUser = authService.findUserByToken(token);
          post.setInstaUser(instaUser);
            instaPostService.addPost(post);
            msg = " Post posted successfully";
            status = HttpStatus.OK;
        }
        else
        {
            msg = "Invalid user";
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<String>(msg , status);

    }

    // GET ALL POST
    @GetMapping("/posts/all/{email}/{token}")
    ResponseEntity<List<InstaPost>> getAllPost(@PathVariable String email, @PathVariable String token){

        HttpStatus status;
        List<InstaPost> instaPostList = null;
        if(authService.authenticate(token,email)){
            instaPostList = instaPostService.fetchAllPost(email);
            status = HttpStatus.OK;
        }
        else{
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<>(instaPostList, status);
    }


}
