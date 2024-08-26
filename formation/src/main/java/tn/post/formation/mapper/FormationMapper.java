package tn.post.formation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import tn.post.formation.dto.DtoFormateurGET;
import tn.post.formation.dto.DtoFormation;
import tn.post.formation.dto.DtoFormationGet;
import tn.post.formation.model.Formation;
import tn.post.formation.model.ThemeFormation;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface FormationMapper {

    @Mapping(source = "themeFormation", target = "themeFormation", qualifiedByName = "themeFormation")
    Formation toFormation(DtoFormation dtoFormation);

    @Mapping(source = "list", target = "formateurs")
    @Mapping(source = "formation.imageUrl", target = "imageUrl")
    @Mapping(source = "formation", target = "montent", qualifiedByName = "mapPayment")
    @Mapping(source = "formation.date", target = "date", qualifiedByName = "formatDate")
    @Mapping(source = "formation.heure", target = "heure", qualifiedByName = "formatHeure")
    @Mapping(source = "formation.themeFormation", target = "theme", qualifiedByName = "themeFormationName")
    DtoFormationGet toDtoFormation(Formation formation, List<DtoFormateurGET> list);



    @Named("mapPayment")
    default String mapPayment(Formation formation) {
        if (Boolean.TRUE.equals(formation.getPayant())) {
            return formation.getMontent() % 1 == 0 ? String.format("%d DT", formation.getMontent().intValue()) : String.format("%.2f DT", formation.getMontent());
        } else {
            return "Gratuit";
        }
    }

    @Named("formatDate")
    default String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    @Named("formatHeure")
    default String formatHeure(LocalTime heure) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        return heure.format(dtf);
    }
    @Named("themeFormationName")
    default List<String> themeFormationName(List<ThemeFormation> formations) {
        return formations.stream()
                .map(ThemeFormation::getName)
                .collect(Collectors.toList());
    }@Named("themeFormation")
    default List<ThemeFormation> themeFormation(List<String> formations) {
        return formations.stream()
                .map(name->new ThemeFormation(null,name))
                .collect(Collectors.toList());
    }
}
