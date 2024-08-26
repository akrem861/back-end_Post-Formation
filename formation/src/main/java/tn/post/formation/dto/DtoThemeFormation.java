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
public class DtoThemeFormation {
    private long id;
    @NotNull(message = "the name Theme Formation could not be null !!!!")
    @NotEmpty(message = "the name Theme Formation could not be empty !!!!")
    @NotBlank(message = "the name Theme Formation could not be blank !!!!")
    private String name;

}
