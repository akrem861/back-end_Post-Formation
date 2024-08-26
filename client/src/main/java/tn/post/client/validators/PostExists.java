package tn.post.client.validators;



import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tn.post.client.repository.PostRepository;

@Component
@RequiredArgsConstructor
public class PostExists implements ConstraintValidator<ExistsPost, String> {

    private final PostRepository postRepository;
    private String context;

    @Override
    public void initialize(ExistsPost existsPost) {
        this.context = existsPost.context();
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
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean exists = postRepository.existsByName(value);


        if ("DtoCandidatPOST".equals(this.context)) {
            if (!exists) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("le poste de condidat n'existe pas")
                        .addConstraintViolation();
                return false;
            }
        } else if ("DtoPostPOST".equals(this.context)) {
            String idStr = getPathVariable("id");
            if(idStr.matches("^\\d+$"))
                return !postRepository.existsByNameAndIdNot(value, Long.valueOf(idStr));
            else if (exists) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Post with this name already exists.")
                        .addConstraintViolation();
                return false;
            }
        }

        return true;
    }

}
