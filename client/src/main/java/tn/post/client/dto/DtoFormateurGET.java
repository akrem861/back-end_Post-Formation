package tn.post.client.dto;


import lombok.*;
import tn.post.client.model.Specialite;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class DtoFormateurGET {
    private Long id;
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String telephone;
    private List<String> specialites_name;
    private String isValid;
    private Set<String> role;
    private Set<String> permissions;
    private String photo;
    private String cv;

}
