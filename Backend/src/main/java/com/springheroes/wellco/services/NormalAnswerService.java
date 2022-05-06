package com.springheroes.wellco.services;

import com.springheroes.wellco.entities.NormalAnswer;
import com.springheroes.wellco.exceptions.SpringRedditException;
import com.springheroes.wellco.repositories.NormalAnswerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NormalAnswerService {

    private final NormalAnswerRepository normalAnswerRepository;

    public NormalAnswer createAnswer(NormalAnswer normalAnswer){
        return normalAnswerRepository.save(normalAnswer);
    }

    public NormalAnswer updateAnswer(NormalAnswer normalAnswer){
        return  normalAnswerRepository.save(normalAnswer);
    }

    public NormalAnswer getNormalAnswer(Long id) {
        return normalAnswerRepository.findById(id)
                .orElseThrow(()->new SpringRedditException("No question was found with given id"));
    }

    public void deleteAnswer(Long id){
        normalAnswerRepository.deleteById(id);
    }

    public List<NormalAnswer> getAllNormalAnswers() {
        return normalAnswerRepository.findAll();
    }
}
