package tn.post.client.dto;

import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import tn.post.client.model.Post;
import tn.post.client.validators.ExistsPost;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class DtoPostPOST {
    private Long id;
    @NotNull(message = "Le nom de la post ne peut être vide !")
    @NotBlank(message = "Le nom de la post ne peut être vide !")
    @ExistsPost(context = "DtoPostPOST")
    private String name;
    @NotNull(message = "Le description de la post ne peut être vide !")
    @NotBlank(message = "Le description de la post ne peut être vide !")
    private String description;


}
