package tn.post.client.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.post.client.IClientService.IPostService;
import tn.post.client.dto.DtoPostGET;
import tn.post.client.dto.DtoPostPOST;
import tn.post.client.exception.PostNotFoundException;
import tn.post.client.model.Post;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
public class PostController {

    private final IPostService service;
    @GetMapping("")
    public ResponseEntity<?> listPost(){
        return service.ListPost();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>postById(@PathVariable Long id) throws PostNotFoundException {
        return service.PostById(id);
    }
    @PostMapping("")
    public ResponseEntity<?>SavePost(@Valid @RequestBody DtoPostPOST post){
        return service.SavePost(post);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?>updatePost(@PathVariable Long id,@Valid @RequestBody DtoPostPOST post) throws PostNotFoundException {
        return service.UpdatePost(id,post);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?>deletePost(@PathVariable Long id) throws PostNotFoundException {
        return service.deletePost(id);
    }
}
