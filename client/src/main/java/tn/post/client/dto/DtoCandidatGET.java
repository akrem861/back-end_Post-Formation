package tn.post.client.dto;


import lombok.*;

import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class DtoCandidatGET {

    private Long id;
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String telephone;
    private String name_post;
    private String photo;
    private Set<String> role;
    private Set<String> permissions;


}
