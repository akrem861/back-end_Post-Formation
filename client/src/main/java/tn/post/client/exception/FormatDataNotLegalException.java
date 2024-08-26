package tn.post.client.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class FormatDataNotLegalException extends RuntimeException{

    private final Set<?> stringSet;

}
