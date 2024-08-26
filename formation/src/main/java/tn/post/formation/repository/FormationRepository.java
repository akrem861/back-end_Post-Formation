package tn.post.formation.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.post.formation.model.Formation;

import java.util.List;

@Repository

public interface FormationRepository extends JpaRepository<Formation,Long> {

}

