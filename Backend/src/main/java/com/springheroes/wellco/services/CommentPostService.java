package com.springheroes.wellco.services;

import com.springheroes.wellco.entities.CommentPost;
import com.springheroes.wellco.entities.NotificationEmail;
import com.springheroes.wellco.entities.NotificationType;
import com.springheroes.wellco.entities.User;
import com.springheroes.wellco.exceptions.SpringRedditException;
import com.springheroes.wellco.repositories.CommentPostRepository;
import com.springheroes.wellco.repositories.PostRepository;
import com.springheroes.wellco.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentPostService {

    private final NotificationService notificationService;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentPostRepository commentPostRepository;
    private final UserServiceImpl authService;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;

    public CommentPost createCommentPost(Long postId, String content){
        CommentPost comment = new CommentPost();
        comment.setText(content);
        comment.setPost(postRepository.findById(postId).get());
        comment.setCreatedDate(new Date());
        /*comment.setUser(authService.getCurrentUser());
        String message = mailContentBuilder.build(comment.getPost().getUser().getUsername() +"Commented on your post:" +
                " " + comment.getPost().getPostId());
        notificationService.generateNotification(NotificationType.POST,message,comment.getPost().getUser());
        sendCommentNotification(message, comment.getPost().getUser());*/
        return commentPostRepository.save(comment);
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificationEmail(user.getUsername() + " Commented on your article", user.getEmail(),
                message));

    }

    public CommentPost updateCommentPost(CommentPost commentPost){
        return commentPostRepository.save(commentPost);

    }
    public CommentPost getCommentPostById(Long id) {
        return commentPostRepository.findById(id)
                .orElseThrow(()->new SpringRedditException("No CommentPost was found with given id"));
    }

    public List<CommentPost> getAllCommentPost(){
        return commentPostRepository.findAll();
    }

    public void deleteCommentPostById(Long id){
        commentPostRepository.deleteById(id);
    }

    public List<CommentPost> getCommentsByPostId(Long postId) {
        return commentPostRepository.findAllByPost_PostId(postId);
    }

}