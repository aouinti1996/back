package com.springheroes.wellco.services;


import com.springheroes.wellco.entities.MBTIAnswer;
import com.springheroes.wellco.exceptions.SpringRedditException;
import com.springheroes.wellco.repositories.MBTIAnswerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MBTIAnswerService {

    private final MBTIAnswerRepository mbtiAnswerRepository;

    public MBTIAnswer createAnswer(MBTIAnswer mbtiAnswer){
        return mbtiAnswerRepository.save(mbtiAnswer);
    }

    public MBTIAnswer updateAnswer(MBTIAnswer mbtiAnswer){
        return  mbtiAnswerRepository.save(mbtiAnswer);
    }

    public MBTIAnswer getMBTIAnswer(Long id) {
        return mbtiAnswerRepository.findById(id)
                .orElseThrow(()->new SpringRedditException("No question was found with given id"));
    }

    public List<MBTIAnswer> getAllMBTIAnswersByQuestionId(Long id){
        return mbtiAnswerRepository.findAllByMbtiQuestion_Id(id);
    }

    public void deleteAnswer(Long id){
        mbtiAnswerRepository.deleteById(id);
    }
}
