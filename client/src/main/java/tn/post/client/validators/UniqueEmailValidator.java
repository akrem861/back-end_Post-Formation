package tn.post.client.validators;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tn.post.client.repository.UtilisateurRepository;

import java.lang.reflect.Field;


@Component
@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, Object> {


    private final UtilisateurRepository repository;


    private Object getFieldValue(Object obj, String fieldName) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true); // Allow access to private fields
            return field.get(obj);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace(); // Handle exceptions as needed
            return null;
        }
    }

    private String getPathVariable(String variableName) {
        HttpServletRequest request = getRequest();
        if (request != null) {
            var x = request.getRequestURI().split("/");
            return x[x.length-1]; // Extract path without context path
        }
        return null;
    }

    private HttpServletRequest getRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        } catch (IllegalStateException e) {
            return null;
        }
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String idStr = getPathVariable("id");
        Long id = 0L;
        if(value.getClass().isAnnotationPresent(UniqueEmail.class)){
            if( idStr == null){
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Vous n'avez pas fourni l'identifiant dans l'url").addConstraintViolation();
                return false;
            }else{
                 id = Long.valueOf(idStr);
            }

          String email = (String)getFieldValue(value,"email");
            return !repository.existsByEmailAndIdNot(email, id);
        }else{
            String email = (String) value;
                return !repository.existsByEmail(email);
        }
    }
}

