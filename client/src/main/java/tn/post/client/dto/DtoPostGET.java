package tn.post.client.dto;



import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class DtoPostGET {
    private Long id;
    private String name;
    private String description;
}
