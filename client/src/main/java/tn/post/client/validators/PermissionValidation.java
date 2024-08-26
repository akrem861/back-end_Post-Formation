package tn.post.client.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import tn.post.client.enumerate.Permission;
import java.util.Set;



public class PermissionValidation implements ConstraintValidator<ValidPermission,Set<String>> {


    @Override
    public boolean isValid(Set<String> permissions, ConstraintValidatorContext context) {

        return permissions.stream()
                .allMatch(this::isPermissionValid);
    }

    private boolean isPermissionValid(String permission) {
        try {
            Permission.valueOf(permission);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
