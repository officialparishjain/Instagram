package com.parishjain.Instagram.repository;

import com.parishjain.Instagram.models.InstaFollower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFollowerRepo extends JpaRepository<InstaFollower,Long> {
}
