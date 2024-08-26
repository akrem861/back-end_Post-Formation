package tn.post.client.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DtoSpecialitePOST {
    private Long id;
    @NotEmpty(message = "Le nom de specialite ne peut être vide !")
    @NotBlank(message = "Le nom de specialite ne peut être vide !")
    private String name;
}
