package tn.post.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.post.client.model.Post;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    boolean existsByName(String name_post);
    Boolean existsByNameAndIdNot(String name,Long id);
    Post findByName(String post_name);



}
