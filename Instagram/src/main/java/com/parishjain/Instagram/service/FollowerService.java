package com.parishjain.Instagram.service;

import com.parishjain.Instagram.models.InstaFollower;
import com.parishjain.Instagram.models.InstaUser;
import com.parishjain.Instagram.repository.IFollowerRepo;
import com.parishjain.Instagram.repository.IFollowingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowerService {

    @Autowired
    IFollowerRepo followerRepo;



    public void saveFollower(InstaUser myUser, InstaUser otherUser) {
        InstaFollower instaFollower = new InstaFollower(null,myUser,otherUser);
        followerRepo.save(instaFollower);
    }
}
