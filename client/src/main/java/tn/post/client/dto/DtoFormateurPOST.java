package tn.post.client.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import tn.post.client.repository.UtilisateurRepository;
import tn.post.client.validators.UniqueEmail;
import tn.post.client.validators.VerifUser;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class DtoFormateurPOST {

    private Long id;
    @NotNull(message = "Le nom ne peut être vide !")
    @NotBlank(message = "Le nom ne peut être vide !")
    private String nom;
    @NotNull(message = "Le prenom ne peut être vide !")
    @NotBlank(message = "Le prenom ne peut être vide !")
    private String prenom;
    @NotNull(message = "Adresse ne peut être vide !")
    @NotBlank(message = "Adresse ne peut être vide !")
    private String adresse;
    @NotNull(message = "email ne peut être vide !")
    @NotBlank(message = "email ne peut être vide !")
    @Email(message = "n'est pas un mail valide !!!!")
    @UniqueEmail
    private String email;
    @NotNull(message = "Mot de passe ne peut être vide !")
    @NotBlank(message = "Mot de passe ne peut être vide !")
    private String password;
    @NotNull(message = "Numéro de téléphone ne peut être vide !")
    @Size(min = 8,max = 8,message = "Numéro de téléphone doit être égal 8 chiffre ")
    private String telephone;
    @NotNull(message = "La spécialité Formateur/Formatrice ne peut être vide !")
    @NotEmpty(message = "La spécialité Formateur/Formatrice ne peut être vide !")
    private List<String> specialite_formateur;
    @AssertTrue(message = "")
    private Boolean isFormateur = true;
    @AssertFalse(message = "")
    private Boolean isValid = false;


}
