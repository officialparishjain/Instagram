package com.parishjain.Instagram.repository;

import com.parishjain.Instagram.models.InstaPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InstaPostRepo extends JpaRepository<InstaPost,Long> {
}
