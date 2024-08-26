package tn.post.client.apiFormation;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tn.post.client.dto.dtoFormation;

import java.util.List;

@FeignClient(name = "dtoFormation-Service",url = "${application.config.formation-url}")
public interface FormationApi {
    @GetMapping("/formation-with-formateur/{id-formateur}")
    List<dtoFormation> validatFormationExist(@PathVariable("id-formateur") Long id);
}
