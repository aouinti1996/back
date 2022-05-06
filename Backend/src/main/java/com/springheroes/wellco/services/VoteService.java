package com.springheroes.wellco.services;

import com.springheroes.wellco.dto.VoteDto;
import com.springheroes.wellco.entities.Article;
import com.springheroes.wellco.entities.Vote;
import com.springheroes.wellco.exceptions.PostNotFoundException;
import com.springheroes.wellco.exceptions.SpringRedditException;
import com.springheroes.wellco.repositories.ArticleRepository;
import com.springheroes.wellco.repositories.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.springheroes.wellco.entities.VoteType.UPVOTE;


@Service
@AllArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final ArticleRepository articleRepository;
    private final UserServiceImpl authService;

    @Transactional
    public void vote(VoteDto voteDto)  {
        Article article = articleRepository.findById(voteDto.getArticleId())
                .orElseThrow(() -> new PostNotFoundException("Article Not Found with ID - " + voteDto.getArticleId()));
        Optional<Vote> voteByPostAndUser = voteRepository.findTopByArticleAndUserOrderByVoteIdDesc(article, authService.getCurrentUser());
        if (voteByPostAndUser.isPresent() &&
                voteByPostAndUser.get().getVoteType()
                        .equals(voteDto.getVoteType())) {
            throw new SpringRedditException("You have already "
                    + voteDto.getVoteType() + "'d this article");
        }
        if (UPVOTE.equals(voteDto.getVoteType())) {
            article.setVoteCount(article.getVoteCount() + 1);
        } else {
            article.setVoteCount(article.getVoteCount() - 1);
        }
        voteRepository.save(mapToVote(voteDto, article));
        articleRepository.save(article);
    }

    private Vote mapToVote(VoteDto voteDto, Article article) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .article(article)
                .user(authService.getCurrentUser())
                .build();
    }
}
