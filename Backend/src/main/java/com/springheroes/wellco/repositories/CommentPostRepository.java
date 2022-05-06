package com.springheroes.wellco.repositories;

import com.springheroes.wellco.entities.CommentPost;
import com.springheroes.wellco.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface CommentPostRepository extends JpaRepository<CommentPost,Long> {
    List<CommentPost> findAllByPost_PostId(Long postId);

}
