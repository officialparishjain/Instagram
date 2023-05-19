package com.parishjain.Instagram.service;

import com.parishjain.Instagram.models.InstaPost;
import com.parishjain.Instagram.repository.InstaPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstaPostService {

    @Autowired
    InstaPostRepo instaPostRepo;

    public List<InstaPost> fetchAllPost(String email) {
        return instaPostRepo.findAll();
    }

    public void addPost(InstaPost post) {
        instaPostRepo.save(post);
    }
}
