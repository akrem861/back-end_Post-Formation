package tn.post.formation.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.post.formation.dto.DtoFormation;
import tn.post.formation.exception.FormationNotFoundException;
import tn.post.formation.exception.ImageException;
import tn.post.formation.exception.UserNotFoundException;
import tn.post.formation.model.Formation;
import tn.post.formation.IService.IFormationService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/formation")
@RequiredArgsConstructor
public class FormationController {


    private final IFormationService service;

    @GetMapping("")
    public ResponseEntity<?> ListFormations(){
        return service.ListFormation();
    }

    @GetMapping("/{IdFormation}")
    public ResponseEntity<?> getFormationById(@PathVariable Long IdFormation) throws FormationNotFoundException {
        return service.FormationById(IdFormation);
    }

    @GetMapping("/with-formateur/{IdFormation}")
    public ResponseEntity<?> getFormationByFormateur(@PathVariable Long IdFormation) throws FormationNotFoundException {
        return service.FormationWithFormateur(IdFormation);
    }

    @PostMapping(value = "")
    public ResponseEntity<?> saveFormation(@RequestPart DtoFormation dtoFormation, @RequestPart(value = "image",required = true)MultipartFile image) throws ImageException {
        return service.SaveFormation(dtoFormation,image);
    }
    @PutMapping("/add-formateur/{id}")
    public ResponseEntity<?> addFormateur(@PathVariable("id") Long idFormation,@RequestParam("id-formateur") Long idFormateur) throws UserNotFoundException, FormationNotFoundException {
        return service.AddFormateurToFormation(idFormation,idFormateur);
    }

    @PutMapping("/{IdFormation}")
    public ResponseEntity<?> UpdateFormation(@PathVariable Long IdFormation ,@RequestPart DtoFormation NewDtoFormation, @RequestPart(value = "image",required = false)MultipartFile image) throws FormationNotFoundException,ImageException {
        return service.UpdateFormation(IdFormation,NewDtoFormation,image);
    }

    @DeleteMapping("/{IdFormation}")
    public ResponseEntity<?> deleteFormation(@PathVariable Long IdFormation) throws FormationNotFoundException {
        return service.DeleteFormation(IdFormation);
    }

}



