package tn.post.client.IClientService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.post.client.dto.DtoPostGET;
import tn.post.client.dto.DtoPostPOST;
import tn.post.client.exception.PostNotFoundException;
import tn.post.client.model.Post;

import java.util.List;
public interface IPostService {
    ResponseEntity<?> SavePost(DtoPostPOST post);

    ResponseEntity<?> ListPost();


    ResponseEntity<?> PostById(Long Idpost) throws PostNotFoundException;

    ResponseEntity<?> UpdatePost(Long Idpost, DtoPostPOST NewPost) throws PostNotFoundException;

    ResponseEntity<?> deletePost(Long Idpost) throws PostNotFoundException;
}
