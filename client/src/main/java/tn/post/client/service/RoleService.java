package tn.post.client.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.post.client.IClientService.IPostService;
import tn.post.client.IClientService.IRoleService;
import tn.post.client.dto.DtoPostPOST;
import tn.post.client.dto.DtoRolePOST;
import tn.post.client.exception.PostNotFoundException;
import tn.post.client.exception.RoleNotFoundException;
import tn.post.client.mapper.PostMapper;
import tn.post.client.mapper.RoleMapper;
import tn.post.client.model.Post;
import tn.post.client.model.Role;
import tn.post.client.repository.PostRepository;
import tn.post.client.repository.RoleRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {

    private final RoleRepository repository;
    private final RoleMapper mapper;
    private Map<String, String> response ;

    @Override
    public ResponseEntity<?> SaveRole(DtoRolePOST dtoRolePOST) {
        Role role = repository.save(mapper.toRole(dtoRolePOST));
        return ResponseEntity.status(201).body(mapper.toDtoRole(role));
    }

    @Override
    public ResponseEntity<?> ListRole() {
        return ResponseEntity.ok().body(mapper.ListRole(repository.findAll()));
    }

    @Override
    public ResponseEntity<?> RoleById(Long IdRole) throws RoleNotFoundException {
        Optional<Role> role  = repository.findById(IdRole);
        if(!role.isPresent())
          throw new RoleNotFoundException("Role not found !!!");
        return ResponseEntity.ok().body(mapper.toDtoRole(role.get()));
    }

    @Override
    public ResponseEntity<?> UpdateRole(Long IdRole, DtoRolePOST  NewRole) throws RoleNotFoundException {
        Optional<Role> role  = repository.findById(IdRole);
        if(!role.isPresent())
            throw new RoleNotFoundException("Role not found !!!");
        NewRole.setId(role.get().getId());
        Role role1 = repository.save(mapper.toRole(NewRole));
        return ResponseEntity.ok().body(mapper.toDtoRole(role1));
    }

    @Override
    public ResponseEntity<?> deleteRole(Long IdRole) throws RoleNotFoundException {
        Optional<Role> role  = repository.findById(IdRole);
        if(!role.isPresent())
            throw new RoleNotFoundException("Role not found !!!");
        repository.deleteById(IdRole);
        response = new HashMap<>();
        response.put("message","Role deleted");
        return ResponseEntity.ok().body(response);
    }



//
//    @Override
//    public ResponseEntity<?> UpdatePost(Long Idpost, DtoPostPOST NewPost) throws PostNotFoundException {
//        Optional<Post> post = repository.findById(Idpost);
//        if(!post.isPresent())
//            throw new PostNotFoundException("Post not found !!!");
//
//        NewPost.setId(post
//                        .get()
//                        .getId());
//        Post post1 = repository.save(mapper.toPost(NewPost));
//        return ResponseEntity
//                .ok()
//                .body(mapper.toDtoPost(post1));
//    }
//
//    @Override
//    public ResponseEntity<?> deletePost(Long Idpost) throws PostNotFoundException {
//        Optional<Post> post = repository.findById(Idpost);
//        if(!post.isPresent())
//            throw new PostNotFoundException("Post not found !!!");
//        repository.deleteById(Idpost);
//        response = new HashMap<>();
//        response.put("message","Post deleted");
//        return ResponseEntity.ok().body(response);
//    }
}
