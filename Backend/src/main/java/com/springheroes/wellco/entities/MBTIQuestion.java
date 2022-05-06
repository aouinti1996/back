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
public class MBTIQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    private String description;

    private String personalityTrait;
    @OneToMany(cascade = CascadeType.ALL,mappedBy= "mbtiQuestion")
    private List<MBTIAnswer> MBTIAnswers;

    @ManyToOne(cascade = CascadeType.ALL)
    private NormalQuiz quiz;
}
