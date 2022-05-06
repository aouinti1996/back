package com.springheroes.wellco.repositories;


import com.springheroes.wellco.entities.Article;
import com.springheroes.wellco.entities.CommentArticle;
import com.springheroes.wellco.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentArticleRepository extends JpaRepository<CommentArticle,Long> {
    List<CommentArticle> findByArticle(Article article);

    List<CommentArticle> findAllByUser(User user);
}
