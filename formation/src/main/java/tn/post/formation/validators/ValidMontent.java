package tn.post.formation.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;

@Constraint(validatedBy = MontentValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
public @interface ValidMontent {
    String message() default "Montent must be greater than 0.0 when payant ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
