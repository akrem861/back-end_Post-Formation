package tn.post.client.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.post.client.validators.UniqueRole;
import tn.post.client.validators.ValidPermission;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@UniqueRole
public class DtoRolePOST {
    private Long id;
    @NotEmpty(message = "Le nom role ne peut être vide !")
    @NotBlank(message = "Le nom role ne peut être vide !")
    private String name;
    @Size(min = 1, message = "Permissions ne peut être vide !")
    @ValidPermission
    private Set<String> permission;



}
