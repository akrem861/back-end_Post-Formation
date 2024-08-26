package tn.post.formation.mapper;

import org.mapstruct.Mapper;
import tn.post.formation.dto.DtoThemeFormation;
import tn.post.formation.dto.DtoThemeFormationGet;
import tn.post.formation.model.ThemeFormation;

@Mapper(componentModel = "spring")
public interface ThemeFormationMapper {

    ThemeFormation toThemeFormation(DtoThemeFormation dtoThemeFormation);

    DtoThemeFormationGet toDtoThemeFormation(ThemeFormation themeFormation);





}
