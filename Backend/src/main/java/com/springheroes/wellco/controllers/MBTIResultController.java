package com.springheroes.wellco.controllers;

import com.springheroes.wellco.entities.MBTIAnswer;
import com.springheroes.wellco.entities.MBTIResult;
import com.springheroes.wellco.services.MBTIResultService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mbti-results")
@AllArgsConstructor
public class MBTIResultController {

    private final MBTIResultService mbtiResultService;

    @PostMapping
    public MBTIResult createResult(@RequestBody MBTIResult mbtiResult){
        return mbtiResultService.createResult(mbtiResult);
    }

    @PutMapping
    public MBTIResult updateResult(@RequestBody MBTIResult mbtiResult){
        return mbtiResultService.updateResult(mbtiResult);
    }

    @GetMapping("/{id}")
    public MBTIResult getResultById(@PathVariable Long id){
        return mbtiResultService.getMBTIResult(id);
    }

    @GetMapping("/{username}")
    public MBTIResult getResultByUsername(@PathVariable String username){
        return mbtiResultService.getMBTIResultByUsername(username);
    }

    @DeleteMapping("/{id}")
    public void deleteResult(@PathVariable Long id){
        mbtiResultService.deleteResult(id);
    }

    @PostMapping("/{id}")
    public String getPersonalityType(@PathVariable Long id, @RequestBody List<MBTIAnswer> answers){
        return mbtiResultService.getPersonalityType(id,answers);
    }
}
