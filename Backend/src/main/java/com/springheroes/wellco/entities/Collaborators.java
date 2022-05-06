package com.springheroes.wellco.entities;

import com.springheroes.wellco.enumeration.Offer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Collaborators {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCollaborator;
    private String name;
    private float rating = 0;
    private int rateTime = 0;
    @Enumerated(EnumType.STRING)
    private CollabType collabType;
    @Enumerated(EnumType.STRING)
    private Offer offer;
    @OneToOne(cascade = CascadeType.ALL)
    private Event currentEvent;
}
