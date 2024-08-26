package tn.post.formation.IService;
import org.springframework.http.ResponseEntity;
import tn.post.formation.dto.DtoThemeFormation;
import tn.post.formation.exception.FormationNotFoundException;
import tn.post.formation.model.ThemeFormation;

import java.util.List;
public interface IThemeFormationService {
    ResponseEntity<?> SaveThemeFormation(DtoThemeFormation dtoThemeFormation);

    ResponseEntity<?> ListThemeFormation();

    ResponseEntity<?> ThemeFormationById(Long IdThemeFormation) throws FormationNotFoundException;

    ResponseEntity<?> UpdateThemeFormation(Long IdThemeFormation, DtoThemeFormation NewDtoThemeFormation) throws FormationNotFoundException;


    ResponseEntity<?> DeleteThemeFormation(Long idThemeFormation) throws FormationNotFoundException;

}
