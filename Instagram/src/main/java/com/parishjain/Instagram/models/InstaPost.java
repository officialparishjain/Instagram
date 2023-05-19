package com.parishjain.Instagram.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
public class InstaPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private Timestamp postDate;

    @Column(nullable = false)
    @NotEmpty
    private String postData;

    private String postCaption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false , name = "fk_user_ID")
    private InstaUser instaUser;
}
