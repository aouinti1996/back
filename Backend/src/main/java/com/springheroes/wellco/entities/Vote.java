package com.springheroes.wellco.entities;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long voteId;

    private VoteType voteType;

    @Nullable
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "articleId", referencedColumnName = "articleId")
    private Article article;
    @Nullable
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    private Post post;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;
}




