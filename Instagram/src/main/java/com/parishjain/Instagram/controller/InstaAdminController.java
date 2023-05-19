package com.parishjain.Instagram.controller;

import com.parishjain.Instagram.models.InstaAdmin;
import com.parishjain.Instagram.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin")
public class InstaAdminController {

    @Autowired
    AdminService adminService;


    // REGISTER ADMIN
    @PostMapping(value = "/add")
    private ResponseEntity<String> addAdmin(@RequestBody InstaAdmin admin){
        HttpStatus status;
        String response;
        try{
            adminService.addAdmin(admin);
            status = HttpStatus.OK;
            response = "Admin has been saved successfully...";
        }
        catch (Exception ex){
            status = HttpStatus.FORBIDDEN;
            response = "Error Occurred " + ex;
        }
        return  ResponseEntity.status(status).body(response);
    }


    // BLUE TICK
    @PutMapping("/user/{id}/{blueTick}")
    ResponseEntity<String> toggleBlueTick(@PathVariable Long id,@PathVariable Boolean blueTick){
        return adminService.toggleBlueTick(id,blueTick);

    }
}
