package com.springheroes.wellco.repositories;


import com.springheroes.wellco.entities.Article;
import com.springheroes.wellco.entities.Category;
import com.springheroes.wellco.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {
    List<Article> findAllByCategory(Category category);

    List<Article> findByUser(User user);
}
