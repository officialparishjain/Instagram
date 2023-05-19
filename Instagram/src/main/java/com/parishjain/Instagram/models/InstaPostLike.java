package com.parishjain.Instagram.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class InstaPostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;


    // MANY LIKES CAN BE ON ONE POST

    @ManyToOne
    @JoinColumn(nullable = false , name = "fk_post_ID")
    private InstaPost instaPost;

    // MANY LIKES CAN BE DONE BY ONE USER
    @ManyToOne
    @JoinColumn(nullable = false , name = "fk_user_ID")
    private InstaUser instaUser;
}
