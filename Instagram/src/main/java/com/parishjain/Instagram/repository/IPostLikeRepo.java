package com.parishjain.Instagram.repository;

import com.parishjain.Instagram.models.InstaPostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostLikeRepo extends JpaRepository<InstaPostLike,Long> {
}
