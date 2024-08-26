package tn.post.formation.service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tn.post.formation.IService.IThemeFormationService;
import tn.post.formation.dto.DtoThemeFormation;
import tn.post.formation.exception.FormatDataNotLegalException;
import tn.post.formation.exception.FormationNotFoundException;
import tn.post.formation.mapper.ThemeFormationMapper;
import tn.post.formation.model.ThemeFormation;
import tn.post.formation.repository.ClientApi;
import tn.post.formation.repository.ThemeFormationRepository;
import tn.post.formation.validators.ObjectValidator;


import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ThemeFormationService implements IThemeFormationService {

    private final ThemeFormationRepository repository;
    private final ObjectValidator<DtoThemeFormation> validator;
    private final ThemeFormationMapper mapper;
    private final ClientApi clientApi;
    private Set<?> validations;
    private Map<String, String> response ;


    @Override
    public ResponseEntity<?> SaveThemeFormation(DtoThemeFormation dtoThemeFormation) {
            validations = validator.Validate(dtoThemeFormation);
        if(!validations.isEmpty()){
            throw new FormatDataNotLegalException(validations);
        }
        ThemeFormation themeFormation = mapper.toThemeFormation(dtoThemeFormation);
        return ResponseEntity.status(201).body(
              mapper.toDtoThemeFormation(repository.save(themeFormation))) ;
    }

    @Override
    public ResponseEntity<?> ListThemeFormation() {
        return ResponseEntity.ok().body(repository.findAll());
    }

    @Override
    public ResponseEntity<?> ThemeFormationById(Long IdThemeFormation) throws FormationNotFoundException {
        Optional<ThemeFormation> themeFormation = repository.findById(IdThemeFormation);
        if(!themeFormation.isPresent())
            throw new FormationNotFoundException("the theme formation not found !!!");
        return ResponseEntity.ok().body(mapper.toDtoThemeFormation(themeFormation.get()));
    }

    @Override
    public ResponseEntity<?> UpdateThemeFormation(Long IdThemeFormation, DtoThemeFormation NewDtoThemeFormation) throws FormationNotFoundException {
        Optional<ThemeFormation> theme_formation_Ouled = repository.findById(IdThemeFormation);
        if(!theme_formation_Ouled.isPresent())
            throw new FormationNotFoundException("the theme formation not found !!!");

        validations = validator.Validate(NewDtoThemeFormation);
        if(!validations.isEmpty()){
            throw new FormatDataNotLegalException(validations);
        }
        NewDtoThemeFormation.
                setId(theme_formation_Ouled
                        .get()
                        .getId());
        ThemeFormation themeFormation = mapper.toThemeFormation(NewDtoThemeFormation);
        return ResponseEntity
                .ok()
                .body(mapper.toDtoThemeFormation(repository.
                        save(themeFormation)));
    }

    @Override
    public ResponseEntity<?> DeleteThemeFormation(Long IdThemeFormation) throws FormationNotFoundException {
        Optional<ThemeFormation> themeFormation = repository.findById(IdThemeFormation);
        if(!themeFormation.isPresent())
            throw new FormationNotFoundException("the theme formation not found !!!");
        repository.deleteById(IdThemeFormation);
        response = new HashMap<>();
        response.put("message","theme formation deleted");
        return ResponseEntity.ok().body(response);
    }





}
