package com.parishjain.Instagram.repository;

import com.parishjain.Instagram.models.AuthenticationToken;
import com.parishjain.Instagram.models.InstaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthenticationRepo extends JpaRepository<AuthenticationToken,Long> {

    @Modifying
    public void deleteByAuthInstaUser(InstaUser instaUser);


    public AuthenticationToken findAuthenticationTokenByAuthToken(String token);

    AuthenticationToken findFirstByAuthToken(String token);
}
