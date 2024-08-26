package tn.post.client.dto;


import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class DtoUserGET {

    private Long id;
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String telephone;



}
