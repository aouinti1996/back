package com.springheroes.wellco.controllers;

import com.springheroes.wellco.entities.MBTIQuiz;
import com.springheroes.wellco.services.MBTIQuizService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/mbti-quiz")
@AllArgsConstructor
public class MBTIQuizController {

    private final MBTIQuizService mbtiQuizService;

    @PostMapping
    public MBTIQuiz addMBTIQuiz(@RequestBody MBTIQuiz mbtiQuiz){
        return mbtiQuizService.createQuiz(mbtiQuiz);
    }

    @PutMapping
    public MBTIQuiz updateMBTIQuiz(@RequestBody MBTIQuiz mbtiQuiz){
        return mbtiQuizService.updateQuiz(mbtiQuiz);
    }

    @GetMapping("/{id}")
    public MBTIQuiz getMBTIQuizById(@PathVariable Long id){
        return mbtiQuizService.getQuizById(id);
    }

    @GetMapping("/{title}")
    public MBTIQuiz getMBTIQuizByTitle(@PathVariable String title){
        return mbtiQuizService.getQuizByTitle(title);
    }

    @DeleteMapping("/{id}")
    public void deleteMBTIQuiz(@PathVariable Long id){
        mbtiQuizService.deleteQuiz(id);
    }
}
