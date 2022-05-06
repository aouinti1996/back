package com.springheroes.wellco.services;

import com.springheroes.wellco.dto.CommentArticleDto;
import com.springheroes.wellco.entities.*;
import com.springheroes.wellco.exceptions.PostNotFoundException;
import com.springheroes.wellco.mappers.CommentArticleMapper;
import com.springheroes.wellco.repositories.ArticleRepository;
import com.springheroes.wellco.repositories.CommentArticleRepository;
import com.springheroes.wellco.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CommentArticleService {


    private final NotificationService notificationService;
    private final ArticleRepository articleRepository;
    private final CommentArticleRepository commentArticleRepository;
    private final UserRepository userRepository;
    private final UserServiceImpl authService;
    private final CommentArticleMapper commentMapper;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;

    public void save(CommentArticleDto commentDto)  {
        Article article = articleRepository.findById(commentDto.getArticleId())
                .orElseThrow(()-> new PostNotFoundException(commentDto.getArticleId().toString()));
        CommentArticle commentArticle = commentMapper.map(commentDto, article,authService.getCurrentUser());
        commentArticleRepository.save(commentArticle);

        String message =
                mailContentBuilder.build(article.getUser().getUsername() + " posted a commentArticle on your article." );
        sendCommentNotification(message, article.getUser());
        notificationService.generateNotification(NotificationType.ARTICLE,message,article.getUser());
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificationEmail(user.getUsername() + " Commented on your article", user.getEmail(),
                message));

    }

    public List<CommentArticleDto> getAllCommentsForPost(Long postId) {
        Article article = articleRepository.findById(postId).orElseThrow(()-> new PostNotFoundException(postId.toString()));
        return commentArticleRepository.findByArticle(article)
                .stream()
                .map(commentMapper::mapToDto).collect(toList());
    }

    public List<CommentArticleDto> getAllCommentsForUser(String userName) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(()-> new UsernameNotFoundException(userName));
        return commentArticleRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toList());
    }
}
