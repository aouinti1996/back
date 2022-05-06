package com.springheroes.wellco.repositories;

import com.springheroes.wellco.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
