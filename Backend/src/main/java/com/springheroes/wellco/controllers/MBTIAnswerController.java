package com.springheroes.wellco.controllers;

import com.springheroes.wellco.entities.MBTIAnswer;
import com.springheroes.wellco.services.MBTIAnswerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mbti-answers")
@AllArgsConstructor
public class MBTIAnswerController {

    private final MBTIAnswerService mbtiAnswerService;

    @PostMapping
    public MBTIAnswer createAnswer(@RequestBody MBTIAnswer mbtiAnswer){
        return mbtiAnswerService.createAnswer(mbtiAnswer);
    }

    @PutMapping
    public MBTIAnswer updateAnswer(@RequestBody MBTIAnswer mbtiAnswer){
        return mbtiAnswerService.updateAnswer(mbtiAnswer);
    }

    @GetMapping("/{id}")
    public MBTIAnswer getAnswerById(@PathVariable Long id){
        return mbtiAnswerService.getMBTIAnswer(id);
    }

    @GetMapping("/{questionId}")
    public List<MBTIAnswer> getAnswerByQuestionId(@PathVariable Long questionId){
        return mbtiAnswerService.getAllMBTIAnswersByQuestionId(questionId);
    }

    @DeleteMapping("/{id}")
    public void deleteAnswer(@PathVariable Long id){
        mbtiAnswerService.deleteAnswer(id);
    }


}
