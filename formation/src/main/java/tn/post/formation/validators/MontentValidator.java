package tn.post.formation.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import tn.post.formation.dto.DtoFormation;


public class MontentValidator implements ConstraintValidator<ValidMontent, DtoFormation> {


    @Override
    public boolean isValid(DtoFormation dto, ConstraintValidatorContext context) {
        if (dto == null) {
            return true;
        }
        if (Boolean.TRUE.equals(dto.getPayant())) {
            return dto.getMontent() != null && dto.getMontent() > 0;
        }
        return true;
    }
}
