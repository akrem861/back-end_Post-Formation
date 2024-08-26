package tn.post.formation.IService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import tn.post.formation.dto.DtoFormation;
import tn.post.formation.exception.FormationNotFoundException;
import tn.post.formation.exception.ImageException;
import tn.post.formation.exception.UserNotFoundException;
import tn.post.formation.model.Formation;

import java.io.IOException;
import java.util.List;

public interface IFormationService {
    ResponseEntity<?> SaveFormation(DtoFormation dtoFormation, MultipartFile image) throws ImageException;

    ResponseEntity<?> ListFormation();

    ResponseEntity<?> FormationById(Long IdFormation) throws FormationNotFoundException;

    ResponseEntity<?> AddFormateurToFormation(Long idFormation,Long idFormateur) throws FormationNotFoundException, UserNotFoundException;

    ResponseEntity<?> FormationWithFormateur(Long id) throws FormationNotFoundException;

    ResponseEntity<?> UpdateFormation(Long IdFormation, DtoFormation NewDtoFormation,MultipartFile image) throws FormationNotFoundException,ImageException;

    ResponseEntity<?> DeleteFormation(Long idFormation) throws FormationNotFoundException;
}