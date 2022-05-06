package com.springheroes.wellco.mappers;

import com.springheroes.wellco.dto.CommentArticleDto;
import com.springheroes.wellco.entities.Article;
import com.springheroes.wellco.entities.CommentArticle;
import com.springheroes.wellco.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentArticleMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "text", source = "commentArticleDto.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "article", source = "article")
    CommentArticle map(CommentArticleDto commentArticleDto, Article article, User user);

    @Mapping(target = "articleId", expression = "java(commentArticle.getArticle().getArticleId())")
    @Mapping(target = "userName", expression = "java(commentArticle.getUser().getUsername())")
    CommentArticleDto mapToDto(CommentArticle commentArticle);
}
