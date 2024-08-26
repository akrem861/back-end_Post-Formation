package tn.post.formation.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.post.formation.model.ThemeFormation;

import java.util.List;

@Repository
public interface ThemeFormationRepository extends JpaRepository<ThemeFormation, Long> {
}
