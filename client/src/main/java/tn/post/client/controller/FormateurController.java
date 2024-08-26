package tn.post.client.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.post.client.IClientService.IUserService;
import tn.post.client.dto.DtoCandidatPOST;
import tn.post.client.dto.DtoFormateurGET;
import tn.post.client.dto.DtoFormateurPOST;
import tn.post.client.dto.DtoSpecialitePOST;
import tn.post.client.exception.ImageException;
import tn.post.client.exception.ProblemServerException;
import tn.post.client.exception.SpecialiteNotFoundException;
import tn.post.client.exception.UserNotFoundException;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/formateur")
@RequiredArgsConstructor
public class FormateurController {
    private final IUserService service;
    @GetMapping("")
    public ResponseEntity<?> listFormateur(){
        return service.ListFormateur();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>formateurById(@PathVariable Long id) throws UserNotFoundException {
        return service.FormateurById(id);
    }
    @GetMapping("verif-formateur/{id}")
    public boolean VerifyFormateur(@PathVariable Long id) {
        return service.verifyFormateurExist(id);
    }
    @GetMapping("formateur-with-formation/{id-formateur}")
    public List<DtoFormateurGET> AllFormateurOfTheFormation(@PathVariable("id-formateur") Long id){
        return service.allFormateurWithFormation(id);
    }
    @PostMapping("")
    public ResponseEntity<?>addFormateur(
            @Valid @RequestPart(value = "user") DtoFormateurPOST user,
            @RequestPart(value = "image") MultipartFile image,
            @RequestPart(value = "cv")MultipartFile cv) throws ProblemServerException, ImageException {
        return service.SaveFormateur(user,image,cv);
    }
    @PutMapping("/specialite/{id}")
    public ResponseEntity<?>UpdateSpec(@Valid Long id, @RequestBody DtoSpecialitePOST dtoSpecialitePOST) throws SpecialiteNotFoundException {
        return service.UpdateSpecialite(id,dtoSpecialitePOST);
    }

    @PutMapping("add-formation/{id-formateur}")
    public void addFormationToFormateur(@PathVariable("id-formateur") Long id, @RequestParam("id-formation") Long idFormation){
        service.addFormationToFormateur(id,idFormation);
    }

}
