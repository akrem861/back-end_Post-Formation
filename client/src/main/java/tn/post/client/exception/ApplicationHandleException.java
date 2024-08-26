package tn.post.client.exception;

import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@AllArgsConstructor
public class ApplicationHandleException {
    private final Map<String, Object> mess ;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            UserNotFoundException.class,
            PostNotFoundException.class,
            RoleNotFoundException.class,
            SpecialiteNotFoundException.class
    })
    public Map<String, Object> handleNotFoundFormation(Exception ex) {
        mess.clear();
        mess.put("errorMessage", ex.getMessage());
        return mess;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ImageException.class)
    public Map<String,Object> handleImageFormation(Exception ex){
        mess.put("errorMessage",ex.getMessage());
        return mess;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ProblemServerException.class})
    public Map<String, ?> handleProblemServer(ProblemServerException ex) {
        var errorMessage = ex.getMessage();
        mess.clear();
        mess.put("errorMessage", errorMessage);
        return mess;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, ?> handleBadInput(MethodArgumentNotValidException ex) {
        Map<String, List> errors = new HashMap<>();
        errors.put("errorMessage",ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList());
        return errors;
    }

}



