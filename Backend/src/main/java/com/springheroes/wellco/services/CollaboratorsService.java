package com.springheroes.wellco.services;

import com.springheroes.wellco.entities.Collaborators;
import com.springheroes.wellco.entities.Event;
import com.springheroes.wellco.exceptions.SpringRedditException;
import com.springheroes.wellco.exceptions.UserNotFoundException;
import com.springheroes.wellco.repositories.CollaboratorsRepository;
import com.springheroes.wellco.repositories.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CollaboratorsService {

    private final CollaboratorsRepository collaboratorsRepository;
    private final EventRepository eventRepository;

    public Collaborators createCollaborators(Collaborators collaborators){
        return collaboratorsRepository.save(collaborators);
    }

    public Collaborators updateCollaborators(Collaborators collaborators){
        return collaboratorsRepository.save(collaborators);
    }


    public List <Collaborators> getAllCollaborators(){
        return collaboratorsRepository.findAll();
    }

    public Collaborators getCollaborators(Integer id){
        return collaboratorsRepository.findById(id)
                .orElseThrow(()->new SpringRedditException("No Collaborator was found with given id"));
    }

    public void deleteCollaborators(Integer id) {
        collaboratorsRepository.deleteById(id);
    }

    public ResponseEntity<Collaborators> getCollaboratorById(@PathVariable(value = "id") Integer collaboratorId) throws UserNotFoundException {
        Collaborators collaborator =
                collaboratorsRepository.findById(collaboratorId).orElseThrow(() -> new UserNotFoundException(
                        "Collaborator not found for this id :: " + collaboratorId));
        return ResponseEntity.ok().body(collaborator);
    }

    public ResponseEntity<Collaborators> updateCollaborator( Integer collaboratorId,
                                                           Collaborators collaboratorDetails) throws UserNotFoundException {
        Collaborators collaborator =
                collaboratorsRepository.findById(collaboratorId).orElseThrow(() -> new UserNotFoundException(
                        "Collaborator not found for this id :: " + collaboratorId));
        collaborator.setName(collaboratorDetails.getName());
        collaborator.setRating(collaboratorDetails.getRating());
        collaborator.setOffer(collaboratorDetails.getOffer());
        collaborator.setCollabType(collaboratorDetails.getCollabType());
        final Collaborators updatedCollaborator = collaboratorsRepository.save(collaborator);
        return ResponseEntity.ok(updatedCollaborator);
    }

    public Map<String, Boolean> deleteCollaborator( Integer collaboratorId) throws UserNotFoundException{
        Collaborators collaborator =
                collaboratorsRepository.findById(collaboratorId).orElseThrow(() -> new UserNotFoundException(
                        "Collaborator not found for this id :: " + collaboratorId));
        collaboratorsRepository.delete(collaborator);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;

    }

    public void rateCollaborator(int collaboratorId,int rate){
        Collaborators collaborator = collaboratorsRepository.findById(collaboratorId)
                .orElseThrow(()->new SpringRedditException("Collaborator not found"));
        if (1<= rate && rate <= 5) {
            collaborator.setRateTime(collaborator.getRateTime()+1);
            collaborator.setRating((collaborator.getRating()+rate)/collaborator.getRateTime());
            collaboratorsRepository.save(collaborator);
        } else {
            throw new SpringRedditException("Rate must be from 1 to 5");
        }
    }

    public void reserveCollaborator(int collaboratorId, Long eventId){
        Collaborators collaborator = collaboratorsRepository.findById(collaboratorId)
                .orElseThrow(()-> new SpringRedditException("Collaborator not found"));
        Event event = eventRepository.findById(eventId).orElseThrow(()-> new SpringRedditException("Event not found"));

        if (collaborator.getCurrentEvent() == null) {
            collaborator.setCurrentEvent(event);
            collaboratorsRepository.save(collaborator);
        } else {
            throw new SpringRedditException("Collaborator not available");
        }
    }

    public void removeReservation(int collaboratorId){
        Collaborators collaborator = collaboratorsRepository.findById(collaboratorId)
                .orElseThrow(()-> new SpringRedditException("Collaborator not found"));
        if (!(collaborator.getCurrentEvent() == null)) {
            collaborator.setCurrentEvent(null);
            collaboratorsRepository.save(collaborator);
        } else {
            throw new SpringRedditException("Collaborator already free");
        }
    }


}
