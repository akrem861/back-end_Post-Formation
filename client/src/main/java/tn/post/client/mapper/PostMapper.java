package tn.post.client.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import tn.post.client.dto.*;
import tn.post.client.model.Post;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    Post toPost(DtoPostPOST dtoPostPOST);

    DtoPostGET toDtoPost(Post post);

    List<DtoPostGET> DtolistPost(List<Post> list);





}
