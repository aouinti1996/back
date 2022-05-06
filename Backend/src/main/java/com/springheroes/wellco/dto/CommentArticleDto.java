package com.springheroes.wellco.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentArticleDto {
    private Long id;
    private Long articleId;
    private Instant createdDate;
    private String text;
    private String userName;
}