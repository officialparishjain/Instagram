package com.parishjain.Instagram.repository;

import com.parishjain.Instagram.models.InstaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstaUserRepo extends JpaRepository<InstaUser,Long> {

    Boolean existsByUserEmail(String email);

    InstaUser findFirstByUserEmail(String email);

    InstaUser findByUserId(Long id);
}
