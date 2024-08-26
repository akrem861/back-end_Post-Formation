package tn.post.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.post.client.model.Post;
import tn.post.client.model.Specialite;

@Repository
public interface SpecialiteRepository extends JpaRepository<Specialite,Long> {

}
