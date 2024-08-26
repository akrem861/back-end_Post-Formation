package tn.post.client.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.post.client.IClientService.IUserService;
import tn.post.client.dto.DtoAdminPOST;
import tn.post.client.dto.DtoCandidatPOST;
import tn.post.client.exception.ProblemServerException;
import tn.post.client.exception.UserNotFoundException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final IUserService service;
    @GetMapping("")
    public ResponseEntity<?> listAdmin(){
        return service.ListAdmin();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>adminById(@PathVariable Long id) throws UserNotFoundException {
        return service.AdminById(id);
    }
    @PostMapping("")
    public ResponseEntity<?>addAdmin(@Valid @RequestBody DtoAdminPOST user) throws ProblemServerException {
        return service.SaveAdmin(user);
    }

}
