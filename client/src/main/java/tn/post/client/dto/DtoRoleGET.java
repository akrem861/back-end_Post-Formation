package tn.post.client.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.post.client.enumerate.Permission;
import tn.post.client.model.Utilisateur;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DtoRoleGET {
    private Long id;
    private String name;
    private Set<String> permission;



}
