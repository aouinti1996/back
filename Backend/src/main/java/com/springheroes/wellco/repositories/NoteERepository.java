package com.springheroes.wellco.repositories;



import com.springheroes.wellco.entities.Event;
import com.springheroes.wellco.entities.NoteE;
import com.springheroes.wellco.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteERepository extends JpaRepository<NoteE, Integer> {
	List<NoteE> findByEvent(Event event);
	List<NoteE> findByUser(User user);
	List<NoteE> findByUserAndEvent(User user, Event event);

}
