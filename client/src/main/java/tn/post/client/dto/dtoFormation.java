package tn.post.client.dto;

import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class dtoFormation {
    private Long id;
    private String titre;
    private String date;
    private String heure;
    private String montent;
    private String description;
    private String lieu;
    // private List<ThemeFormation> themeFormation;


}