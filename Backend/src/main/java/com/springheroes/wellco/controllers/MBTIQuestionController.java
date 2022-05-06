package com.springheroes.wellco.controllers;

import com.springheroes.wellco.entities.MBTIQuestion;
import com.springheroes.wellco.services.MBTIQuestionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mbti-questions")
@AllArgsConstructor
public class MBTIQuestionController {

    private final MBTIQuestionService mbtiQuestionService;

    @PostMapping
    public MBTIQuestion createMBTIQuestion(@RequestBody MBTIQuestion mbtiQuestion){
        return mbtiQuestionService.createQuestion(mbtiQuestion);
    }

    @PutMapping
    public MBTIQuestion updateMBTIQuestion(@RequestBody MBTIQuestion mbtiQuestion){
        return mbtiQuestionService.updateQuestion(mbtiQuestion);
    }

    @GetMapping
    public List<MBTIQuestion> getAllMBTIQuestions(){
        return mbtiQuestionService.getAllMBTIQuestions();
    }

    @GetMapping("/{id}")
    public MBTIQuestion getMBTIQuestion(@PathVariable Long id){
        return  mbtiQuestionService.getMBTIQuestion(id);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id){
        mbtiQuestionService.deleteQuestion(id);
    }
}
