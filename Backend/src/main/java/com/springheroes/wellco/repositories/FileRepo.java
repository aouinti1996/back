package com.springheroes.wellco.repositories;

import com.springheroes.wellco.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepo extends JpaRepository<File, Integer> {
}
