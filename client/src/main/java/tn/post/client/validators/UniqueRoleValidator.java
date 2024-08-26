package tn.post.client.validators;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tn.post.client.dto.DtoRolePOST;
import tn.post.client.repository.RoleRepository;
import tn.post.client.repository.UtilisateurRepository;

import java.lang.reflect.Field;


@Component
@RequiredArgsConstructor
public class UniqueRoleValidator implements ConstraintValidator<UniqueRole, DtoRolePOST> {


    private final RoleRepository repository;



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
    public boolean isValid(DtoRolePOST value, ConstraintValidatorContext context) {
        String idStr = getPathVariable("id");
        String name = value.getName();
        if(idStr.matches("^\\d+$"))
            return !repository.existsByNameAndIdNot(name, Long.valueOf(idStr));
        else{
            return !repository.existsByName(name);
        }
    }
}


