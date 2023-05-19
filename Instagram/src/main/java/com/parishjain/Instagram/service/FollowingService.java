package com.parishjain.Instagram.service;

import com.parishjain.Instagram.models.InstaFollowing;
import com.parishjain.Instagram.models.InstaUser;
import com.parishjain.Instagram.repository.IFollowingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowingService {
    @Autowired
    IFollowingRepo followingRepo;
    public void saveFollowing(InstaUser instaUser, InstaUser otherUser) {

        InstaFollowing instaFollowing = new InstaFollowing(null,instaUser,otherUser);
        followingRepo.save(instaFollowing);
    }
}
