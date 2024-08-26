package tn.post.formation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import tn.post.formation.model.Formation;
import tn.post.formation.model.ThemeFormation;
import tn.post.formation.validators.ValidMontent;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DtoFormationGet {

    private Long id;
    private String titre;
    private String date;
    private String heure;
    private String montent;
    private String description;
    private String lieu;
    private String imageUrl;
    private List<String> theme;
    private List<DtoFormateurGET> formateurs;


}
