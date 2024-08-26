package tn.post.formation.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
@AllArgsConstructor
public class ApplicationHandleException {
    private final   Map<String,Object> mess = new HashMap<>() ;


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({FormationNotFoundException.class, UserNotFoundException.class})
    public Map<String,Object> handleNotFoundFormation(Exception ex){
            mess.put("errorMessage",ex.getMessage());
        return mess;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ImageException.class)
    public Map<String,Object> handleImageFormation(Exception ex){
            mess.put("errorMessage",ex.getMessage());
        return mess;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FormatDataNotLegalException.class)
    public Map<String,?> handleBadFormatInput(FormatDataNotLegalException ex){
        var errorMessage = ex.getStringSet();
        mess.put("errorMessage",errorMessage);
        return mess;
    }


}
