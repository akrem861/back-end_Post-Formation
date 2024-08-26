package tn.post.formation.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import tn.post.formation.model.ThemeFormation;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DtoThemeFormationGet {
    private long id;
    private String name;


}
