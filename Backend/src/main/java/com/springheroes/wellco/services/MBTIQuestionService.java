package com.springheroes.wellco.services;

import com.springheroes.wellco.entities.MBTIQuestion;
import com.springheroes.wellco.exceptions.SpringRedditException;
import com.springheroes.wellco.repositories.MBTIQuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MBTIQuestionService {

    private final MBTIQuestionRepository mbtiQuestionRepository;

    public MBTIQuestion createQuestion(MBTIQuestion mbtiQuestion){
        return mbtiQuestionRepository.save(mbtiQuestion);
    }

    public MBTIQuestion updateQuestion(MBTIQuestion mbtiQuestion){
        return  mbtiQuestionRepository.save(mbtiQuestion);
    }

    public List<MBTIQuestion> getAllMBTIQuestions(){
        return mbtiQuestionRepository.findAll();
    }

    public MBTIQuestion getMBTIQuestion(Long id) {
        return mbtiQuestionRepository.findById(id)
                .orElseThrow(()->new SpringRedditException("No question was found with given id"));
    }

    public void deleteQuestion(Long id){
        mbtiQuestionRepository.deleteById(id);
    }
}
