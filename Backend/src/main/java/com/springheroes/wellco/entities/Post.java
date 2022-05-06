package com.springheroes.wellco.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String description;
    private int likes;
    private  int dislikes;
    private Integer voteCount;
    private Date createdDate;
    private String FileName;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> sharers;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> likers;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> dislikers;
    @JsonIgnore
    @OneToOne
    private ImagePost file;
}
