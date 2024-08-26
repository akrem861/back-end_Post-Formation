package tn.post.client.validators;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PostExists.class)
@Target({ElementType.FIELD,ElementType.METHOD,})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsPost {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String context() default "default";

}
