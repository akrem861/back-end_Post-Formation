package tn.post.formation.exception;

import lombok.*;

import java.lang.module.FindException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class FormatDataNotLegalException extends RuntimeException{

    private final Set<?> stringSet;

}
