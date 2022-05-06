package com.springheroes.wellco.controllers;

import com.springheroes.wellco.entities.NormalAnswer;
import com.springheroes.wellco.services.NormalAnswerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/normal-answers")
public class NormalAnswerController {

    private final NormalAnswerService normalAnswerService;

    @PostMapping
    public NormalAnswer createNormalAnswer(@RequestBody NormalAnswer normalAnswer){
        return normalAnswerService.createAnswer(normalAnswer);
    }

    @PutMapping
    public NormalAnswer updateNormalAnswer(@RequestBody NormalAnswer normalAnswer){
        return normalAnswerService.updateAnswer(normalAnswer);
    }

    @GetMapping
    public List<NormalAnswer> getAllNormalAnswers(){
        return normalAnswerService.getAllNormalAnswers();
    }

    @GetMapping("/{id}")
    public NormalAnswer getNormalAnswer(@PathVariable Long id){
        return  normalAnswerService.getNormalAnswer(id);
    }


    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id){
        normalAnswerService.deleteAnswer(id);
    }
}
