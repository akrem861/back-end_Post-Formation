package tn.post.client.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.post.client.IClientService.IRoleService;
import tn.post.client.dto.DtoRolePOST;
import tn.post.client.exception.RoleNotFoundException;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
public class RoleController {

    private final IRoleService service;
    @GetMapping("")
    public ResponseEntity<?> listRole(){
        return service.ListRole();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>RoleById(@PathVariable Long id) throws RoleNotFoundException {
        return service.RoleById(id);
    }
    @PostMapping("")
    public ResponseEntity<?>SaveRole(@Valid @RequestBody DtoRolePOST Role){
        return service.SaveRole(Role);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?>updateRole(@PathVariable Long id,@Valid @RequestBody DtoRolePOST Role) throws RoleNotFoundException {
        return service.UpdateRole(id,Role);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteRole(@PathVariable Long id) throws RoleNotFoundException {
        return service.deleteRole(id);
    }
}
