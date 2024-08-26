package tn.post.client.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.post.client.IClientService.IUserService;
import tn.post.client.dto.DtoCandidatPOST;
import tn.post.client.dto.DtoUpdateUser;
import tn.post.client.exception.ImageException;
import tn.post.client.exception.ProblemServerException;
import tn.post.client.exception.UserNotFoundException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/candidat")
@RequiredArgsConstructor

public class CandidatController {
    private final IUserService service;
    @GetMapping("")
    public ResponseEntity<?> listCandidat(){
        return service.ListCandidat();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>candidatById(@PathVariable Long id) throws UserNotFoundException {
        return service.CandidatById(id);
    }
    @PostMapping("")
    public ResponseEntity<?>addCandidat(
            @Valid @RequestPart("user") DtoCandidatPOST user,
            @RequestPart(value = "image") MultipartFile image) throws ProblemServerException , ImageException {
        return service.SaveCandidat(user,image);
    }

}
