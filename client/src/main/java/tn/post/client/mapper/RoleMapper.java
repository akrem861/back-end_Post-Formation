package tn.post.client.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import tn.post.client.dto.DtoPostGET;
import tn.post.client.dto.DtoPostPOST;
import tn.post.client.dto.DtoRoleGET;
import tn.post.client.dto.DtoRolePOST;
import tn.post.client.model.Post;
import tn.post.client.model.Role;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role toRole(DtoRolePOST dtoRolePOST);

    DtoRoleGET toDtoRole(Role role);

    List<DtoRoleGET> ListRole(List<Role> roles);





}
