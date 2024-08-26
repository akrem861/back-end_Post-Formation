package tn.post.client.IClientService;

import org.springframework.http.ResponseEntity;
import tn.post.client.dto.DtoPostPOST;
import tn.post.client.dto.DtoRolePOST;
import tn.post.client.exception.PostNotFoundException;
import tn.post.client.exception.RoleNotFoundException;

public interface IRoleService {
    ResponseEntity<?> SaveRole(DtoRolePOST dtoRolePOST);
    ResponseEntity<?> ListRole();
    ResponseEntity<?> RoleById(Long IdRole) throws RoleNotFoundException;
    ResponseEntity<?> UpdateRole(Long IdRole, DtoRolePOST NewRole) throws RoleNotFoundException;
    ResponseEntity<?> deleteRole(Long IdRole) throws RoleNotFoundException;
}
