package com.springheroes.wellco.repositories;

import com.springheroes.wellco.entities.Article;
import com.springheroes.wellco.entities.Post;
import com.springheroes.wellco.entities.User;
import com.springheroes.wellco.entities.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote,Long> {
    Optional<Vote> findTopByArticleAndUserOrderByVoteIdDesc(Article article, User currentUser);

    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
