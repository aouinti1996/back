package com.springheroes.wellco.controllers;


import com.springheroes.wellco.entities.NormalQuestion;
import com.springheroes.wellco.services.NormalQuestionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/api/normal-questions")
public class NormalQuestionController {

    private final NormalQuestionService normalQuestionService;

    @PostMapping
    public NormalQuestion createNormalQuestion(@RequestBody NormalQuestion normalQuestion){
        return normalQuestionService.createQuestion(normalQuestion);
    }

    @PutMapping
    public NormalQuestion updateNormalQuestion(@RequestBody NormalQuestion normalQuestion){
        return normalQuestionService.updateQuestion(normalQuestion);
    }

    @GetMapping
    public List<NormalQuestion> getAllNormalQuestionzes(){
        return normalQuestionService.getAllNormalQuestions();
    }

    @GetMapping("/{id}")
    public NormalQuestion getNormalQuestion(@PathVariable Long id){
        return  normalQuestionService.getNormalQuestion(id);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id){
        normalQuestionService.deleteQuestion(id);
    }
}
