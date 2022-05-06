package com.springheroes.wellco.services;


import com.springheroes.wellco.entities.Event;
import com.springheroes.wellco.entities.Office;
import com.springheroes.wellco.entities.User;
import com.springheroes.wellco.enumeration.EventNote;
import com.springheroes.wellco.enumeration.EventType;

import java.util.List;

public interface IEventService {
	
	Event addEvent(Event e, EventType eventType, Integer idOffice);
	Office addOffice(Office off);
	List<Event> listOfEvent();
	List<User> getUsersByComment(Long idEvent);
	List<User> getUsersByLikes(Long IdEvent);
	void deleteEvent(Long idEvent);
	
	void commentEvent(Long idEvent, Long idUser, String Comment);
	void uncommentEvent(Integer idComment);
	
	void noteEvent(Long idEvent, EventNote eventNote, Long idUser);
	void removeNote(Integer idNote, Long idEvent);
	
	void likeEvent(Long idEvent, Long idUser);
	void removeLikeEvent(Long idEvent, Long idUser);
	
	void joinEvent(Long idEvent, Long idUser);
	void unjoinEvent(Long idEvent, Long idUser);
	
	List<User> getUsersByCI(Long idUser);
	List<Event> getEventByOffice(Integer idOffice);
	
	void AddEventToFav(Long idEvent, Long idUser);
	
	//void saveImage(MultipartFile imageFile) throws Exception;


}
