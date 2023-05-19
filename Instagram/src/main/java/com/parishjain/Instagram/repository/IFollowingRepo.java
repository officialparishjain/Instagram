package com.parishjain.Instagram.repository;

import com.parishjain.Instagram.models.InstaFollowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFollowingRepo extends JpaRepository<InstaFollowing,Long> {
}
