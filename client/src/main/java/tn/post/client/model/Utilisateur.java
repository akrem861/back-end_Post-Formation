package tn.post.client.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.Set;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String password;
    private String telephone;
    private Boolean isCandidat = false;
    private Boolean isFormateur = false;
    private Boolean isValid = false;
    private Boolean isAdmin = false;
    private String cv;
    private String photo;


    @OneToMany(cascade = CascadeType.ALL)
    private List<Specialite> specialites;

    @JsonManagedReference
    @ManyToOne(targetEntity = Post.class)
    private Post post;

    @ElementCollection
    private Set<Long> listFormation;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY,cascade ={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName ="id" )
    )
    private Set<Role> roles;


}
