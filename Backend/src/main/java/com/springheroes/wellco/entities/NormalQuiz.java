package com.springheroes.wellco.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NormalQuiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quiz")
    private List<NormalQuestion> normalQuestions;

    private int passingScore;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> participants;
}


