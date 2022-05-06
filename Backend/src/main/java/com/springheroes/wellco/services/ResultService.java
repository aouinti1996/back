package com.springheroes.wellco.services;

import com.springheroes.wellco.entities.NormalAnswer;
import com.springheroes.wellco.entities.NormalQuiz;
import com.springheroes.wellco.entities.Result;
import com.springheroes.wellco.exceptions.SpringRedditException;
import com.springheroes.wellco.repositories.ResultRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ResultService {

    private final ResultRepository resultRepository;

    public Result createResult(Result result){
        return resultRepository.save(result);
    }

    public Result updateResult(Result result){
        return  resultRepository.save(result);
    }

    public Result getResult(Long id) {
        return resultRepository.findById(id)
                .orElseThrow(()->new SpringRedditException("No question was found with given id"));
    }

    public void deleteResult(Long id){
        resultRepository.deleteById(id);
    }

    public void setPassingScore(Long id,int score){
        NormalQuiz quiz = resultRepository.findById(id).get().getQuiz();
        if( score <= quiz.getNormalQuestions().size() && score >= quiz.getNormalQuestions().size()/2) {
            quiz.setPassingScore(score);
        }
    }

    public void setResult(Long id, List<NormalAnswer> answers) {
        Result result = resultRepository.findById(id)
                .orElseThrow(()-> new SpringRedditException("No result found with given id"));
        int score = 0;
        for (NormalAnswer answer : answers){
            if (answer.isCorrect()){
                score= score+1;
            }
        }
        if (score >= result.getQuiz().getPassingScore()){
            result.setPassed(true);
        }
    }

}
