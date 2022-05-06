package com.springheroes.wellco.mappers;

import com.github.marlonlom.utilities.timeago.TimeAgo;

import com.springheroes.wellco.dto.ArticleRequest;
import com.springheroes.wellco.dto.ArticleResponse;
import com.springheroes.wellco.entities.*;
import com.springheroes.wellco.repositories.CommentArticleRepository;
import com.springheroes.wellco.repositories.VoteRepository;
import com.springheroes.wellco.services.UserServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static com.springheroes.wellco.entities.VoteType.DOWNVOTE;
import static com.springheroes.wellco.entities.VoteType.UPVOTE;


@Mapper(componentModel = "spring")
public abstract class ArticleMapper {

    @Autowired
    private CommentArticleRepository commentArticleRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private UserServiceImpl authService;


    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "articleRequest.description")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "voteCount", constant = "0")
    @Mapping(target = "user", source = "user")
    public abstract Article map(ArticleRequest articleRequest, Category category, User user);

    @Mapping(target = "id", source = "articleId")
    @Mapping(target = "categoryName", source = "category.name")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "commentCount", expression = "java(commentCount(article))")
    @Mapping(target = "duration", expression = "java(getDuration(article))")
    @Mapping(target = "upVote", expression = "java(isArticleUpVoted(article))")
    @Mapping(target = "downVote", expression = "java(isArticleDownVoted(article))")
    public abstract ArticleResponse mapToDto(Article article) ;

    Integer commentCount(Article article) {
        return commentArticleRepository.findByArticle(article).size();
    }

    String getDuration(Article article) {
        return TimeAgo.using(article.getCreatedDate().toEpochMilli());
    }

    boolean isArticleUpVoted(Article article)  {
        return checkVoteType(article, UPVOTE);
    }

    boolean isArticleDownVoted(Article article)  {
        return checkVoteType(article, DOWNVOTE);
    }

    private boolean checkVoteType(Article article, VoteType voteType)  {
        if (authService.isLoggedIn()) {
            Optional<Vote> voteForPostByUser =
                    voteRepository.findTopByArticleAndUserOrderByVoteIdDesc(article,
                            authService.getCurrentUser());
            return voteForPostByUser.filter(vote -> vote.getVoteType().equals(voteType))
                    .isPresent();
        }
        return false;
    }
}
