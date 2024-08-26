package tn.post.formation.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tn.post.formation.dto.DtoFormateurGET;

import java.util.List;


@FeignClient(name = "Client-service",url = "${application.config.client-url}")

public interface ClientApi {
    @GetMapping("verif-formateur/{id}")
    Boolean verifyFormateurExiste(@PathVariable Long id);

    @PutMapping("add-formation/{id-formateur}")
    void addFormationToFormateur(@PathVariable("id-formateur") Long id, @RequestParam("id-formation") Long idFormation);

    @GetMapping("formateur-with-formation/{id-formateur}")
    List<DtoFormateurGET> listFormateurOfFormation(@PathVariable("id-formateur") Long id);

}
