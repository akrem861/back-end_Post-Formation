package tn.post.formation.service;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tn.post.formation.IService.IFormationService;
import tn.post.formation.dto.DtoFormateurGET;
import tn.post.formation.dto.DtoFormation;

import tn.post.formation.dto.DtoFormationGet;
import tn.post.formation.exception.FormatDataNotLegalException;
import tn.post.formation.exception.FormationNotFoundException;
import tn.post.formation.exception.ImageException;
import tn.post.formation.exception.UserNotFoundException;
import tn.post.formation.mapper.FormationMapper;
import tn.post.formation.model.Formation;

import tn.post.formation.repository.ClientApi;
import tn.post.formation.repository.FormationRepository;
import tn.post.formation.validators.ObjectValidator;


import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FormationService implements IFormationService {

    private final FormationRepository repository;
    private final ObjectValidator<DtoFormation> validator;
    private final ClientApi clientApi;
    private final FormationMapper mapper;
    private final ImageService imageService;

    private Set<?> validations;
    private Map<String, String> response ;

    @Transactional
    @Override
    public ResponseEntity<?> SaveFormation(DtoFormation dtoFormation, MultipartFile image) throws ImageException {
        validations = validator.Validate(dtoFormation);
        if(!validations.isEmpty()){
            throw new FormatDataNotLegalException(validations);
        }
        if(!dtoFormation.getPayant())
            dtoFormation.setMontent(0.0);
        Formation formation = mapper.toFormation(dtoFormation);
        formation.setImageUrl(imageService.addPhoto(image));
        return ResponseEntity.status(201).body(
                mapper.toDtoFormation(repository.save(formation),null));

    }

    @Override
    public ResponseEntity<?> ListFormation() {
        return ResponseEntity.ok().body( repository.findAll().stream().map(formation -> {
            List<DtoFormateurGET> formateurs = clientApi.listFormateurOfFormation(formation.getId());
            return mapper.toDtoFormation(formation, formateurs.isEmpty() ? null : formateurs);
        }).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<?> FormationById(Long IdFormation) throws FormationNotFoundException {
        Optional<Formation> formation  = repository.findById(IdFormation);
        if(!formation.isPresent())
            throw new FormationNotFoundException("Formation not found !!!");
        return ResponseEntity.ok().body(mapper.toDtoFormation(formation.get(),null));
    }
    @Transactional
    @Override
    public ResponseEntity<?> AddFormateurToFormation(Long idFormation,Long idFormateur) throws FormationNotFoundException, UserNotFoundException {
        Optional<Formation> formation = repository.findById(idFormation);
        if(!formation.isPresent())
            throw new FormationNotFoundException("Formation not found !!!");
        if(!clientApi.verifyFormateurExiste(idFormateur))
            throw new UserNotFoundException("Formateur not found");
        formation.get().getListIdFormateurs().add(idFormateur);
        clientApi.addFormationToFormateur(idFormateur,idFormation);
        return ResponseEntity.ok().body(mapper.toDtoFormation(repository.save(formation.get()),null));
    }
    @Override
    public ResponseEntity<?> FormationWithFormateur(Long id) throws FormationNotFoundException {
       Optional<Formation>  formation = repository.findById(id);
        if(!formation.isPresent()){
            throw new FormationNotFoundException("Formation not found !!!");
        }
         List<DtoFormateurGET> list =clientApi.listFormateurOfFormation(id);
       return ResponseEntity.ok().body(mapper.toDtoFormation(formation.get(),list));
    }

    @Transactional
    @Override
    public ResponseEntity<?> UpdateFormation(Long IdFormation, DtoFormation NewDtoFormation,MultipartFile image) throws FormationNotFoundException,ImageException {
        Optional<Formation> formationOld = repository.findById(IdFormation);
        if(!formationOld.isPresent())
            throw new FormationNotFoundException("Formation not found !!!");

        validations = validator.Validate(NewDtoFormation);
        if(!validations.isEmpty()){
            throw new FormatDataNotLegalException(validations);
        }
        NewDtoFormation.
                setId(formationOld
                        .get()
                        .getId());
        if(!NewDtoFormation.getPayant())
            NewDtoFormation.setMontent(0.0);
        Formation formation = mapper.toFormation(NewDtoFormation);
        formation.setImageUrl(imageService.addPhoto(image));
        return ResponseEntity
                .ok()
                .body(mapper.toDtoFormation(repository.save(formation),null));
    }

    @Override
    public ResponseEntity<?> DeleteFormation(Long IdFormation) throws FormationNotFoundException {
        Optional<Formation> formation = repository.findById(IdFormation);
        if(!formation.isPresent())
            throw new FormationNotFoundException("Formation not found !!!");
        repository.deleteById(IdFormation);
        response = new HashMap<>();
        response.put("message","formation deleted");
        return ResponseEntity.ok().body(response);
    }




}