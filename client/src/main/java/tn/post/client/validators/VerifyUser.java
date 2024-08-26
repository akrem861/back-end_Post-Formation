package tn.post.client.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tn.post.client.dto.DtoCandidatPOST;
import tn.post.client.repository.PostRepository;

@Component
@RequiredArgsConstructor
public class VerifyUser implements ConstraintValidator<VerifUser, DtoCandidatPOST> {

    private final PostRepository repository;

    private String messagePost = "Le poste ne peut être vide !";
    private String messagePostInvalid = "Le poste n'existe pas";
    private String messageSpecialite = "La spécialité Formateur/Formatrice ne peut être vide !";
    private String messageRole = "Choisir s'il s'agit d'un candidat, d'un formateur ou d'un administrateur";

    @Override
    public void initialize(VerifUser constraintAnnotation) {

    }

    @Override
    public boolean isValid(DtoCandidatPOST dto, ConstraintValidatorContext context) {
        return true;
    }
}
