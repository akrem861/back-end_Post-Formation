package tn.post.client.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import tn.post.client.enumerate.Permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PermissionValidation.class)
@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPermission {
    String message() default "une ou plusieurs autorisations n'ont pas été utilisées !!!!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
