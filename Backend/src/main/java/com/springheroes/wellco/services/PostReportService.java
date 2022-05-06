package com.springheroes.wellco.services;

import com.springheroes.wellco.entities.PostReport;
import com.springheroes.wellco.repositories.PostReportRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostReportService {

    private final PostReportRepository postReportRepository;

    public PostReport addPostReport(PostReport postReport){
        return postReportRepository.save(postReport);
    }

    public List<PostReport> getAllPostReportsByUsername(String username){
        return postReportRepository.findAllByIssuer_Username(username);
    }

    public PostReport updatePostReport(PostReport postReport){
        return postReportRepository.save(postReport);
    }

    public void deletePostReport(Long id){
        postReportRepository.deleteById(id);
    }



}
