package com.springheroes.wellco.controllers;

import com.springheroes.wellco.entities.PostReport;
import com.springheroes.wellco.services.PostReportService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportPost")
@AllArgsConstructor
public class PostReportController {

    private final PostReportService postReportService;

    @PostMapping
    public PostReport reportPost(@RequestBody PostReport postReport){
        return postReportService.addPostReport(postReport);
    }

    @PutMapping
    public PostReport updateReport(@RequestBody PostReport postReport){
        return postReportService.updatePostReport(postReport);
    }

    @DeleteMapping("/{reportId}")
    public void deleteReport(@PathVariable Long reportId){
        postReportService.deletePostReport(reportId);
    }

    @GetMapping("/{username}")
    public List<PostReport> getPostReportsByUsername(@PathVariable String username){
        return postReportService.getAllPostReportsByUsername(username);
    }
}
