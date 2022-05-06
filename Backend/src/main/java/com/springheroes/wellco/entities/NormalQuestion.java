package com.springheroes.wellco.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NormalQuestion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    private String description;
    @OneToMany(cascade = CascadeType.ALL,mappedBy= "normalQuestion")
    private List<NormalAnswer> normalAnswers;

    @ManyToOne(cascade = CascadeType.ALL)
    private NormalQuiz quiz;
}
