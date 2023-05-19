package com.parishjain.Instagram.repository;

import com.parishjain.Instagram.models.InstaAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminRepo extends JpaRepository<InstaAdmin,Long> {
}
