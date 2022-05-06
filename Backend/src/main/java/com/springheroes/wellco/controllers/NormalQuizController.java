package com.springheroes.wellco.controllers;


import com.springheroes.wellco.entities.NormalQuiz;
import com.springheroes.wellco.services.NormalQuizService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/normal-quiz")
@AllArgsConstructor
public class NormalQuizController {

    private final NormalQuizService normalQuizService;

    @PostMapping
    public NormalQuiz createNormalQuiz(@RequestBody NormalQuiz normalQuiz){
        return normalQuizService.createQuiz(normalQuiz);
    }

    @PutMapping
    public NormalQuiz updateNormalQuiz(@RequestBody NormalQuiz normalQuiz){
        return normalQuizService.updateQuiz(normalQuiz);
    }

    @GetMapping
    public List<NormalQuiz> getAllNormalQuizzes(){
        return normalQuizService.getAllNormalQuizzes();
    }

    @GetMapping("/{id}")
    public NormalQuiz getNormalQuiz(@PathVariable Long id){
        return  normalQuizService.getQuizById(id);
    }

    @GetMapping("/{title}")
    public NormalQuiz getNormalQuizByTitle(@PathVariable String title){
        return  normalQuizService.getQuizByTitle(title);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id){
        normalQuizService.deleteQuiz(id);
    }
}
