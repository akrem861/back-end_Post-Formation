package tn.post.formation.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class UserNotFoundException extends Exception{
    private final String message;




}
