package tn.post.client.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import tn.post.client.dto.DtoPostGET;
import tn.post.client.dto.DtoPostPOST;
import tn.post.client.dto.DtoSpecialiteGET;
import tn.post.client.dto.DtoSpecialitePOST;
import tn.post.client.model.Post;
import tn.post.client.model.Specialite;

@Mapper(componentModel = "spring")
public interface SpecialiteMapper {

    Specialite toSpecialite(DtoSpecialitePOST dtoSpecialitePOST);

    DtoSpecialiteGET toDtoSpecialite(Specialite specialite);





}
