package tn.post.client.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.post.client.IClientService.IPostService;
import tn.post.client.dto.DtoPostPOST;
import tn.post.client.exception.PostNotFoundException;
import tn.post.client.mapper.PostMapper;
import tn.post.client.model.Post;
import tn.post.client.repository.PostRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService implements IPostService {

    private final PostRepository repository;
    private final PostMapper mapper;
    private Map<String, String> response ;
    @Override
    public ResponseEntity<?> SavePost(DtoPostPOST post) {
        Post post1 = repository.save(mapper.toPost(post));
        return ResponseEntity.status(201).body(mapper.toDtoPost(post1));
    }

    @Override
    public ResponseEntity<?> ListPost() {
        return ResponseEntity.ok().body(mapper.DtolistPost(repository.findAll()));
    }

    @Override
    public ResponseEntity<?> PostById(Long Idpost) throws PostNotFoundException {
        Optional<Post> post  = repository.findById(Idpost);
        if(!post.isPresent())
            throw new PostNotFoundException("Post not found !!!");
        return ResponseEntity.ok().body(mapper.toDtoPost(post.get()));
    }

    @Override
    public ResponseEntity<?> UpdatePost(Long Idpost, DtoPostPOST NewPost) throws PostNotFoundException {
        Optional<Post> post = repository.findById(Idpost);
        if(!post.isPresent())
            throw new PostNotFoundException("Post not found !!!");

        NewPost.setId(post
                        .get()
                        .getId());
        Post post1 = repository.save(mapper.toPost(NewPost));
        return ResponseEntity
                .ok()
                .body(mapper.toDtoPost(post1));
    }

    @Override
    public ResponseEntity<?> deletePost(Long Idpost) throws PostNotFoundException {
        Optional<Post> post = repository.findById(Idpost);
        if(!post.isPresent())
            throw new PostNotFoundException("Post not found !!!");
        repository.deleteById(Idpost);
        response = new HashMap<>();
        response.put("message","Post deleted");
        return ResponseEntity.ok().body(response);
    }
}
