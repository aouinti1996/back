package com.springheroes.wellco.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springheroes.wellco.entities.ImagePost;
import com.springheroes.wellco.entities.Post;
import com.springheroes.wellco.repositories.ImagePostRepository;
import com.springheroes.wellco.services.PostService;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin("http://localhost:4200")
@AllArgsConstructor
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    ImagePostRepository imagePostRepository;

    @Autowired
    ServletContext context;

    ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @PostMapping(value = "/add", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Post> createPost(@RequestPart("post") String post, @RequestPart("file") MultipartFile file) throws IOException {

        boolean isExit = new File(context.getRealPath("/Images/")).exists();
        if (!isExit)
        {
            new File (context.getRealPath("/Images/")).mkdir();
            System.out.println("mk dir.............");
        }
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
        File serverFile = new File (context.getRealPath("/Images/"+File.separator+newFileName));
        try
        {
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile,file.getBytes());

        }catch(Exception e) {
            e.printStackTrace();
        }

        ImagePost NpostImage = new ImagePost();
        NpostImage.setImageUrl(context.getRealPath("Images/"+File.separator+newFileName));
        NpostImage.setData(file.getBytes());
        NpostImage.setName(file.getOriginalFilename());
        NpostImage.setType(file.getContentType());
        ImagePost imagePost = postService.saveImage(NpostImage);
        Post Npost = objectMapper.readValue(post, Post.class);
        Npost.setFile(imagePost);
        Npost.setFileName(file.getOriginalFilename());

        postService.save(Npost);
        return new ResponseEntity<>(Npost, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
        return status(HttpStatus.OK).body(postService.getPost(id));
    }

    @GetMapping("by-user/{username}")
    public ResponseEntity<List<Post>> getPostsByUsername(@PathVariable String username) {
        return status(HttpStatus.OK).body(postService.getPostsByUsername(username));
    }

    @PostMapping("/add-like/{postId}")
    public void addLike(@PathVariable Long postId){
        postService.likePostByID(postId);
    }

    @PostMapping("/add-dislike/{postId}")
    public void addDislike(@PathVariable Long postId){
        postService.dislikePostByID(postId);
    }

    @PostMapping("/remove-like/{postId}")
    public void removeLike(@PathVariable Long postId){
        postService.removeLike(postId);
    }

    @PostMapping("/remove-dislike/{postId}")
    public void removeDislike(@PathVariable Long postId){
        postService.removeDislike(postId);
    }

    @PostMapping("/share/{userId}/{postId}")
    public void sharePost(@PathVariable Long userId,@PathVariable Long postId){
        postService.sharePost(userId,postId);
    }

    @DeleteMapping("/delete/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }


    @GetMapping(path="/imagePost/{id}")
    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
        Post post = postService.getPost(id);
        return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+post.getFileName()));
    }
}

