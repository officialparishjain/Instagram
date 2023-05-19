package com.parishjain.Instagram.service;

import com.parishjain.Instagram.models.InstaAdmin;
import com.parishjain.Instagram.repository.IAdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    IAdminRepo adminRepo;

    @Autowired
    InstaUserService instaUserService;

    public void addAdmin(InstaAdmin admin) {
        adminRepo.save(admin);
    }

    public ResponseEntity<String> toggleBlueTick(Long id, Boolean blueTick) {
        return instaUserService.toggleBlueTick(id,blueTick);
    }
}
