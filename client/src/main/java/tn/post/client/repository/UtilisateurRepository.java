package tn.post.client.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.post.client.model.Utilisateur;

import java.util.List;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email,Long id);
    boolean existsByIdAndIsFormateurTrue(Long id);

    Utilisateur findByIdAndIsFormateurTrue(Long id);
    List<Utilisateur> findAllByIsCandidatTrue();
    List<Utilisateur> findAllByIsFormateurTrue();
    List<Utilisateur> findAllByIsAdminTrue();
    List<Utilisateur> findAllByListFormationContaining(Long idFormation);


}
