package com.springheroes.wellco.repositories;


import com.springheroes.wellco.entities.CommentEvent;
import com.springheroes.wellco.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentEventRepository extends JpaRepository<CommentEvent, Integer> {

	//@Query("select c from CommentEvent c where c.idEvent=: idEvnt")
	List<CommentEvent> findByEvent(Event event);
	
	
	
}
