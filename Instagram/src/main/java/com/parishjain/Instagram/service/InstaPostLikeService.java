package com.parishjain.Instagram.service;

import com.parishjain.Instagram.models.InstaPostLike;
import com.parishjain.Instagram.repository.IPostLikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstaPostLikeService {

    @Autowired
    IPostLikeRepo postLikeRepo;


    public void like(InstaPostLike postLike) {
        postLikeRepo.save(postLike);
    }
}
