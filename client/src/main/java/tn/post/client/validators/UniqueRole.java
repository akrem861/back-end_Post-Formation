package tn.post.client.validators;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy =UniqueRoleValidator.class)
@Target({ ElementType.FIELD, ElementType.TYPE,})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueRole {
    String message() default "The role already exists !!!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

