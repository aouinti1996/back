package com.springheroes.wellco.services;

import com.springheroes.wellco.entities.MBTIAnswer;
import com.springheroes.wellco.entities.MBTIResult;
import com.springheroes.wellco.exceptions.SpringRedditException;
import com.springheroes.wellco.repositories.MBTIResultRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MBTIResultService {

    private final MBTIResultRepository mbtiResultRepository;

    public MBTIResult createResult(MBTIResult mbtiResult){
        return mbtiResultRepository.save(mbtiResult);
    }

    public MBTIResult updateResult(MBTIResult mbtiResult){
        return  mbtiResultRepository.save(mbtiResult);
    }

    public MBTIResult getMBTIResult(Long id) {
        return mbtiResultRepository.findById(id)
                .orElseThrow(()->new SpringRedditException("No result was found with given id"));
    }

    public MBTIResult getMBTIResultByUsername(String username){
        return mbtiResultRepository.findByUser_Username(username)
                .orElseThrow(()->new SpringRedditException("No result was found with given username"));
    }

    public void deleteResult(Long id){
        mbtiResultRepository.deleteById(id);
    }

    public String getPersonalityType(Long id, List<MBTIAnswer> answers){

        MBTIResult result = mbtiResultRepository.findById(id)
                .orElseThrow(()-> new SpringRedditException("No result found with given id"));

        String personalityType ="";
        int Iscore = 0;
        int Escore = 0;
        int Nscore = 0;
        int Sscore = 0;
        int Tscore = 0;
        int Fscore = 0;
        int Pscore = 0;
        int Jscore = 0;
        String mindResult ="";
        String energyResult = "";
        String natureResult = "";
        String tacticsResult = "";

        for (MBTIAnswer answer : answers) {
            switch (answer.getPersonalityTraitType()){
                case "I":
                    Iscore = Iscore + answer.getScore();
                    break;
                case "E":
                    Escore = Escore + answer.getScore();
                    break;
                case "N":
                    Nscore = Nscore + answer.getScore();
                    break;
                case "S":
                    Sscore += answer.getScore();
                    break;
                case "T":
                    Tscore += answer.getScore();
                    break;
                case "F":
                    Fscore += answer.getScore();
                    break;
                case "P":
                    Pscore += answer.getScore();
                    break;
                case "J":
                    Jscore += answer.getScore();
                    break;
                default:
                    throw new SpringRedditException("Answer with wrong trait type");
            }
        }

        if (Escore > Iscore){
            mindResult = "E";
        } else {
            mindResult = "I";
        }

        if (Sscore > Nscore){
            energyResult = "S";
        } else {
            energyResult = "N";
        }

        if (Tscore > Fscore) {
            natureResult = "T";
        } else if (Tscore == Fscore) {
            if (result.getUser().getGender().toLowerCase().equals("male")){
                natureResult = "T";
            } else {
                natureResult = "F";
            }
        } else {
            natureResult = "F";
        }

        if (Jscore > Pscore) {
            tacticsResult = "J";
        } else {
            tacticsResult = "P";
        }

        personalityType = mindResult + energyResult + natureResult + tacticsResult;
        result.setPersonalityType(personalityType);
        mbtiResultRepository.save(result);

        return personalityType;
    }
}
