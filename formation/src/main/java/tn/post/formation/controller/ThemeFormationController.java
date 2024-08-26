package tn.post.formation.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.post.formation.dto.DtoThemeFormation;
import tn.post.formation.exception.FormationNotFoundException;
import tn.post.formation.model.ThemeFormation;
import tn.post.formation.IService.IThemeFormationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/theme-formation")
@RequiredArgsConstructor
public class ThemeFormationController {
    private final IThemeFormationService service;


    @GetMapping("")
    public ResponseEntity<?> ListThemeFormation(){
        return service.ListThemeFormation();
    }

    @GetMapping("/{IdThemeFormation}")
    public ResponseEntity<?> getById(
            @PathVariable Long IdThemeFormation) throws FormationNotFoundException {
        return service.ThemeFormationById(IdThemeFormation);
    }

    @PostMapping("")
    public ResponseEntity<?> addThemeFormation(
            @RequestBody DtoThemeFormation dtoThemeFormation){
        return service.SaveThemeFormation(dtoThemeFormation);
    }


    @PutMapping("/{IdThemeFormation}")
    public ResponseEntity<?> UpdateThemeFormation(
             @PathVariable Long IdThemeFormation
            ,@RequestBody DtoThemeFormation dtoThemeFormation)
            throws FormationNotFoundException {
        return service.UpdateThemeFormation(IdThemeFormation,dtoThemeFormation);
    }

    @DeleteMapping("/{IdThemeFormation}")
    public ResponseEntity<?> deleteThemeFormation(
            @PathVariable Long IdThemeFormation) throws FormationNotFoundException {
        return service.DeleteThemeFormation(IdThemeFormation);
    }

}





