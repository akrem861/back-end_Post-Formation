package tn.post.client.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.post.client.IClientService.IUserService;
import tn.post.client.dto.DtoCandidatPOST;
import tn.post.client.dto.DtoUpdateUser;
import tn.post.client.exception.UserNotFoundException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UtilisateurController {
    private final IUserService service;
    @GetMapping("")
    public ResponseEntity<?> listUser(){
        return service.ListUser();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>Userbyid(@PathVariable Long id) throws UserNotFoundException {
        return service.UserById(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?>updateUser(@PathVariable Long id,@Valid @RequestBody DtoUpdateUser user) throws UserNotFoundException {
        return service.UpdateUser(id,user);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteUser(@PathVariable Long id) throws UserNotFoundException {
        return  service.deleteUser(id);
    }
}
