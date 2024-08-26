package tn.post.formation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import tn.post.formation.model.Formation;
import tn.post.formation.model.ThemeFormation;
import tn.post.formation.validators.ValidMontent;

import java.time.LocalTime;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ValidMontent
public class DtoFormation {

    private Long id;
    @NotNull(message = "the titre Formation could not be null !!!!")
    @NotBlank(message = "the titre Formation could not be blank !!!!")
    private String titre;
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "the date Formation could not be null !!!!")
    private Date date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @NotNull(message = "the heure Formation could not be null !!!!")
    private LocalTime heure;
    private Boolean payant = false;
    private Double montent = 0.0;
    @NotNull(message = "the description Formation could not be null !!!!")
    @NotBlank(message = "the description Formation could not be blank !!!!")
    private String description;
    @NotNull(message = "the lieu Formation could not be null !!!!")
    @NotBlank(message = "the lieu Formation could not be blank !!!!")
    private String lieu;
    private Set<Long> listIdFormateurs;
    @Size(min = 1,message = "theme formation must not empty !!!!")
    private List<String> themeFormation;



}
