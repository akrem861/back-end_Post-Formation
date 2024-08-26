package tn.post.formation.exception;

import jakarta.persistence.MappedSuperclass;
import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class FormationNotFoundException extends Exception{
    private final String message;




}
