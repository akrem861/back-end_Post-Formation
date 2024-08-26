package tn.post.client.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tn.post.client.IClientService.IUserService;
import tn.post.client.dto.*;
import tn.post.client.exception.ImageException;
import tn.post.client.exception.ProblemServerException;
import tn.post.client.exception.SpecialiteNotFoundException;
import tn.post.client.exception.UserNotFoundException;
import tn.post.client.mapper.SpecialiteMapper;
import tn.post.client.mapper.UtilisateurMapper;
import tn.post.client.model.Post;
import tn.post.client.model.Role;
import tn.post.client.model.Specialite;
import tn.post.client.model.Utilisateur;
import tn.post.client.repository.PostRepository;
import tn.post.client.repository.RoleRepository;
import tn.post.client.repository.SpecialiteRepository;
import tn.post.client.repository.UtilisateurRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UtilisateurService implements IUserService {

    private final UtilisateurRepository repository;
    private final PostRepository postRepository;
    private final SpecialiteRepository specialiteRepository;
    private final RoleRepository roleRepository;
    private final UtilisateurMapper mapper;
    private final SpecialiteMapper specialiteMapper;
    private final ImageService imageService;
    private Map<String, String> response ;
    private Set<Role> setRole = new HashSet<>();

    @Transactional
    @Override
    public ResponseEntity<?> SaveCandidat(DtoCandidatPOST dtoCandidatPOST, MultipartFile image) throws ProblemServerException, ImageException {
        setRole.clear();
        Utilisateur user = mapper.toUtilisateurCandidat(dtoCandidatPOST);
        Post post = postRepository.findByName(user.getPost().getName());
        Role role = roleRepository.findRoleByName("candidat");
        if(role==null)
            throw new ProblemServerException("problem in the server !!!");
        user.setPost(post);
        setRole.add(role);
        user.setRoles(setRole);
        user.setPhoto(imageService.addPhoto(image));
        Utilisateur utilisateurWithRoles = repository.save(user);
        return ResponseEntity.status(201).body(mapper.toCandidat(utilisateurWithRoles));
    }

    @Transactional
    @Override
    public ResponseEntity<?> SaveFormateur(DtoFormateurPOST dtoFormateurPOST, MultipartFile image, MultipartFile cv) throws ProblemServerException, ImageException{
        Utilisateur user = mapper.toUtilisateurFormateur(dtoFormateurPOST);
        setRole.clear();
        Role role =roleRepository.findRoleByName("formateur");
        if(role==null)
            throw new ProblemServerException("problem in the server !!!");
        setRole.add(role);
        user.setRoles(setRole);
        user.setPhoto(imageService.addPhoto(image));
        user.setCv(imageService.addfile(cv));
        Utilisateur utilisateur = repository.save(user);
        return ResponseEntity.status(201).body(mapper.toFormateur(utilisateur));
    }

    @Transactional
    @Override
    public ResponseEntity<?> SaveAdmin(DtoAdminPOST dtoAdminPOST) throws ProblemServerException{
        Utilisateur user = mapper.toUtilisateurAdmin(dtoAdminPOST);
        setRole.clear();
        Role role =roleRepository.findRoleByName("admin");
        if(role==null)
            throw new ProblemServerException("problem in the server !!!");
        setRole.add(role);
        user.setRoles(setRole);
        Utilisateur utilisateur = repository.save(user);
        return ResponseEntity.status(201).body(mapper.toAdministrateur(utilisateur));
    }


    public Boolean verifyFormateurExist(Long id) {
        return repository.existsByIdAndIsFormateurTrue(id);
    }

    public List<DtoFormateurGET> allFormateurWithFormation(Long idFormation){
       return mapper.toListFormateurs(repository.findAllByListFormationContaining(idFormation)) ;
    }

    @Override
    public void addFormationToFormateur(Long idFormateur,Long idFormation){
       Utilisateur formateur = repository.findByIdAndIsFormateurTrue(idFormateur);
       formateur.getListFormation().add(idFormation);
       repository.save(formateur);
    }


    @Override
    public ResponseEntity<?> ListUser() {
        return ResponseEntity.ok().body(
                mapper.toListUser(repository.findAll()));
    }

    @Override
    public ResponseEntity<?> ListCandidat() {
        return ResponseEntity.ok().body(
                mapper.toListOfCandidat(repository.findAllByIsCandidatTrue()));
    }

    @Override
    public ResponseEntity<?> ListFormateur() {
        return ResponseEntity.ok().body(
        mapper.toListFormateurs(repository.findAllByIsFormateurTrue()));
    }

    @Override
    public ResponseEntity<?> ListAdmin() {
        return ResponseEntity.ok().body(
                mapper.toListAdministrateur(repository.findAllByIsAdminTrue()));
    }

    @Override
    public ResponseEntity<?> CandidatById(Long IdUser) throws UserNotFoundException {
        Optional<Utilisateur> user = repository.findById(IdUser);
        if(!user.isPresent() || !user.get().getIsCandidat())
            throw new UserNotFoundException("Utilisateur not found !!!");
        return ResponseEntity.ok().body(mapper.toCandidat(user.get()));
    }

    @Override
    public ResponseEntity<?> UserById(Long IdUser) throws UserNotFoundException {
        Optional<Utilisateur> user = repository.findById(IdUser);
        if(!user.isPresent())
            throw new UserNotFoundException("Utilisateur not found !!!");
        return ResponseEntity.ok().body(mapper.toUser(user.get()));
    }

    @Override
    public ResponseEntity<?> FormateurById(Long IdUser) throws UserNotFoundException {
        Optional<Utilisateur> user = repository.findById(IdUser);
        if(!user.isPresent() || !user.get().getIsFormateur())
            throw new UserNotFoundException("Utilisateur not found !!!");
        return ResponseEntity.ok().body(mapper.toFormateur(user.get()));
    }

    @Override
    public ResponseEntity<?> AdminById(Long IdUser) throws UserNotFoundException {
        Optional<Utilisateur> user = repository.findById(IdUser);
        if(!user.isPresent() || !user.get().getIsAdmin())
            throw new UserNotFoundException("Utilisateur not found !!!");
        return ResponseEntity.ok().body(mapper.toAdministrateur(user.get()));
    }

    @Override
    public ResponseEntity<?> UpdateUser(Long IdUser, DtoUpdateUser NewdtoUpdateUser) throws UserNotFoundException {
        Optional<Utilisateur> user = repository.findById(IdUser);
        if(!user.isPresent())
            throw new UserNotFoundException("Utilisateur not found !!!");
        Utilisateur util = mapper.toUtilisateur(NewdtoUpdateUser);
        util.setId(user.get().getId());
        util.setPost(user.get().getPost());
        util.setSpecialites(user.get().getSpecialites());
        util.setRoles(user.get().getRoles());

        Utilisateur utilisateur = repository.save(util);
        return ResponseEntity
                .ok()
                .body(mapper.toCandidat(utilisateur));
    }

    @Override
    public ResponseEntity<?> UpdateSpecialite(Long IdSpecialite, DtoSpecialitePOST dtoSpecialitePOST) throws SpecialiteNotFoundException {
        Optional<Specialite> specialite = specialiteRepository.findById(IdSpecialite);
        if(!specialite.isPresent())
            throw new SpecialiteNotFoundException("Specialite not found !!!");
        Specialite spec = specialiteMapper.toSpecialite(dtoSpecialitePOST);
        spec.setId(specialite.get().getId());
        Specialite specialite1 = specialiteRepository.save(spec);
        return ResponseEntity
                .ok()
                .body(specialiteMapper.toDtoSpecialite(specialite1));
    }

    @Override
    public ResponseEntity<?> deleteUser(Long IdUser) throws UserNotFoundException {
        Optional<Utilisateur> user = repository.findById(IdUser);
        if(!user.isPresent())
            throw new UserNotFoundException("Utilisateur not found !!!");
        repository.deleteById(IdUser);
        response = new HashMap<>();
        response.put("message","Utilisateur deleted");
        return ResponseEntity.ok().body(response);
    }
}
