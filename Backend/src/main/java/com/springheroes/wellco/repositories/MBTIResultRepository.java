package com.springheroes.wellco.repositories;

import com.springheroes.wellco.entities.MBTIResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MBTIResultRepository extends JpaRepository<MBTIResult,Long> {

    Optional<MBTIResult> findByUser_Username(String username);
}
