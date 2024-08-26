package tn.post.formation.model;

import jakarta.persistence.*;
import lombok.*;
import tn.post.formation.dto.DtoThemeFormation;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
@Entity
public class ThemeFormation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;




    }