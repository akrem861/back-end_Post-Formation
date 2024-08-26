package tn.post.formation.model;
import jakarta.persistence.*;
import lombok.*;
import tn.post.formation.dto.DtoFormation;
import tn.post.formation.dto.DtoThemeFormation;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalTime;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titre;
    @Temporal(TemporalType.DATE)
    private Date date;
    @Temporal(TemporalType.TIME)
    private LocalTime heure;
    private Boolean payant;
    private Double montent;
    private String description;
    private String lieu;
    private String imageUrl;
    @ElementCollection
    private Set<Long> listIdFormateurs = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<ThemeFormation> themeFormation;







}
