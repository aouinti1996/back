package com.springheroes.wellco.repositories;

import com.springheroes.wellco.entities.ImagePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagePostRepository extends JpaRepository<ImagePost,Integer> {
}
