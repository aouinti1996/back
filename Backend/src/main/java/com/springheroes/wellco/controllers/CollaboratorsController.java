package com.springheroes.wellco.controllers;

import com.springheroes.wellco.entities.Collaborators;
import com.springheroes.wellco.exceptions.UserNotFoundException;
import com.springheroes.wellco.services.CollaboratorsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Collaborators")
@AllArgsConstructor
public class CollaboratorsController {

    private final CollaboratorsService collaboratorsService;

    @PostMapping
    public Collaborators createCollaborators(@RequestBody Collaborators collaborators){
        return collaboratorsService.createCollaborators(collaborators);
    }

    @PutMapping
    public Collaborators updateCollaborators(@RequestBody Collaborators collaborators){
        return collaboratorsService.updateCollaborators(collaborators);
    }

    @GetMapping
    public List<Collaborators> getAllCollaborators(){
        return collaboratorsService.getAllCollaborators();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Collaborators> getCollaboratorById(@PathVariable Integer id) throws UserNotFoundException {
        return collaboratorsService.getCollaboratorById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCollaborators(@PathVariable Integer id)  {
        collaboratorsService.deleteCollaborators(id);
    }

    @PostMapping("/add-reservation/{collaboratorId}/{eventId}")
    private void reserveCollaborator(@PathVariable int collaboratorId, @PathVariable Long eventId){
        collaboratorsService.reserveCollaborator(collaboratorId,eventId);
    }

    @PostMapping("/removeReservation/{collaboratorId}")
    public void removeReservation(@PathVariable int collaboratorId){
        collaboratorsService.removeReservation(collaboratorId);
    }
}

