package tn.post.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.post.client.model.Post;
import tn.post.client.model.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Boolean existsByName(String name);
    Boolean existsByNameAndIdNot(String name,Long id);

    Role findRoleByName(String name);
}
