package com.springheroes.wellco.repositories;

import com.springheroes.wellco.entities.Post;
import com.springheroes.wellco.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findByUser(User user);

    List<Post> findByUser_Department(String string);

    List<Post> findAllByUser_Username(String username);
}
