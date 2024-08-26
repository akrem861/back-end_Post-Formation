package tn.post.client.mapper;

import org.mapstruct.*;
import tn.post.client.dto.*;
import tn.post.client.enumerate.Permission;
import tn.post.client.model.Role;
import tn.post.client.model.Specialite;
import tn.post.client.model.Utilisateur;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {

    @Mapping(source = "post_name", target = "post.name")
    @Mapping(source = "isCandidat", target = "isCandidat")
    Utilisateur toUtilisateurCandidat(DtoCandidatPOST dtoCandidatPOST);

    @Mapping(source = "specialite_formateur", target = "specialites", qualifiedByName = "mapSpecialites")
    @Mapping(source = "isFormateur", target = "isFormateur")
    @Mapping(source = "isValid", target = "isValid")
    Utilisateur toUtilisateurFormateur(DtoFormateurPOST dtoFormateurPOST);

    @Mapping(source = "isAdmin", target = "isAdmin")
    Utilisateur toUtilisateurAdmin(DtoAdminPOST dtoAdminPOST);

    Utilisateur toUtilisateur(DtoUpdateUser dtoUpdateUser);

    @Mapping(target = "specialites_name", source = "specialites", qualifiedByName = "mapSpecialiteNames")
    @Mapping(target = "isValid", source = "isValid", qualifiedByName = "validation")
    @Mapping(target = "role", source = "roles", qualifiedByName = "mapRoleNames")
    @Mapping(target = "permissions", source = "roles", qualifiedByName = "mapPermessionNames")
    DtoFormateurGET toFormateur(Utilisateur utilisateur);

    @Mapping(target = "name_post", source = "post.name")
    @Mapping(target = "role", source = "roles", qualifiedByName = "mapRoleNames")
    @Mapping(target = "permissions", source = "roles", qualifiedByName = "mapPermessionNames")
    DtoCandidatGET toCandidat(Utilisateur utilisateur);

    @Mapping(target = "role", source = "roles", qualifiedByName = "mapRoleNames")
    @Mapping(target = "permissions", source = "roles", qualifiedByName = "mapPermessionNames")
    DtoAdministrateurGET toAdministrateur(Utilisateur utilisateur);

    DtoUserGET toUser(Utilisateur utilisateur);

    List<DtoUserGET> toListUser(List<Utilisateur> utilisateur);
    List<DtoCandidatGET> toListOfCandidat(List<Utilisateur> utilisateurs);
    List<DtoFormateurGET> toListFormateurs(List<Utilisateur> utilisateurs);
    List<DtoAdministrateurGET> toListAdministrateur(List<Utilisateur> utilisateurs);

    @Named("mapSpecialites")
    default List<Specialite> mapSpecialites(List<String> specialiteNames) {
        return specialiteNames.stream()
                .map(name -> new Specialite(null, name)) // Assuming Specialite constructor takes (id, name)
                .collect(Collectors.toList());
    }

    @Named("mapSpecialiteNames")
    default List<String> mapSpecialiteNames(List<Specialite> specialites) {
        return specialites.stream()
                .map(Specialite::getName)
                .collect(Collectors.toList());
    }

    @Named("mapRoleNames")
    default Set<String> mapRoleNames(Set<Role> roles) {
        return roles.stream()
                .map(Role::getName) // Ensure this method exists in your Role class
                .collect(Collectors.toSet());
    }

    @Named("mapPermessionNames")
    default Set<String> mapPermessionNames(Set<Role> roles) {
        return roles.stream()
                .flatMap(role -> role.getPermission().stream())
                .map(Enum::toString)
                .collect(Collectors.toSet());
    }

    @Named("validation")
    default String formateurValidation(Boolean isValid) {
        if (isValid == null || !isValid) {
            return "False";
        } else {
            return "True";
        }
    }
}
