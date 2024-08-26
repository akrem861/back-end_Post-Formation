package tn.post.client.IClientService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import tn.post.client.dto.*;
import tn.post.client.exception.ImageException;
import tn.post.client.exception.ProblemServerException;
import tn.post.client.exception.SpecialiteNotFoundException;
import tn.post.client.exception.UserNotFoundException;

import java.util.List;

public interface IUserService {
    ResponseEntity<?> SaveCandidat(DtoCandidatPOST dtoCandidatPOST,MultipartFile image) throws ProblemServerException, ImageException;
    ResponseEntity<?> SaveFormateur(DtoFormateurPOST dtoFormateurPOST, MultipartFile image,MultipartFile cv) throws ProblemServerException, ImageException;
    ResponseEntity<?> SaveAdmin(DtoAdminPOST dtoAdminPOST) throws ProblemServerException;
    Boolean verifyFormateurExist(Long id);
    void addFormationToFormateur(Long idFormateur,Long idFormation);
    List<DtoFormateurGET> allFormateurWithFormation(Long idFormation);
    ResponseEntity<?> ListUser();
    ResponseEntity<?> ListCandidat();
    ResponseEntity<?> ListFormateur();
    ResponseEntity<?> ListAdmin();

    ResponseEntity<?> CandidatById(Long IdUser)throws UserNotFoundException;
    ResponseEntity<?> FormateurById(Long IdUser)throws UserNotFoundException;
    ResponseEntity<?> AdminById(Long IdUser)throws UserNotFoundException;
    ResponseEntity<?> UserById(Long IdUser)throws UserNotFoundException;

    ResponseEntity<?> UpdateUser(Long IdUser ,  DtoUpdateUser NewdtoUpdateUser)throws UserNotFoundException;
    ResponseEntity<?> UpdateSpecialite(Long IdSpecialite, DtoSpecialitePOST dtoSpecialitePOST) throws SpecialiteNotFoundException;

    ResponseEntity<?> deleteUser(Long IdUser)throws UserNotFoundException;
}
